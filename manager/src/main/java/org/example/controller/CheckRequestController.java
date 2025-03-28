package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.CheckRequest;
import org.example.pojo.Cost;
import org.example.pojo.MedicalTechnology;
import org.example.query.CheckRequestQuery;
import org.example.service.ICheckRequestService;
import org.example.service.ICostService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@RestController
@RequestMapping("/checkrequest")
public class CheckRequestController extends BaseController<CheckRequest, CheckRequestQuery> {

    @Autowired
    ICheckRequestService checkRequestService;

    /*
    根据挂号id查询检查申请列表
     */
    @GetMapping("/reg/{rid}")
    public ResultBean getByRegId(@PathVariable("rid") Integer regId){

        MPJLambdaWrapper<CheckRequest> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.setAlias("cr")
                    .selectAll(CheckRequest.class,"cr")
                    .selectAs(MedicalTechnology::getTechName,"'medicalTechnology.techName'")
                    .selectAs(MedicalTechnology::getTechPrice,"'medicalTechnology.techPrice'")
                    .leftJoin(MedicalTechnology.class,"m",MedicalTechnology::getId,CheckRequest::getMedicalTechnologyId)
                    .eq("register_id",regId);

        List<CheckRequest> list = checkRequestService.list(queryWrapper);
        return ResultBean.SUCCESS(list);
    }

    @PostMapping("/conditions/{rid}")
    public ResultBean getByConditions(@RequestBody CheckRequestQuery checkRequestQuery,@PathVariable("rid") Integer rid){

        checkRequestQuery.setRegisterId(rid);
        List<CheckRequest> list = checkRequestService.list(checkRequestQuery.getQueryWrapper());
        return ResultBean.SUCCESS(list);
    }

    @Autowired
    ICostService costService;

    /**
     * 批量添加检查申请
     * @param requestList
     * @return
     */
    @Transactional
    @PostMapping("/addbatch")
    public ResultBean addBatch(@RequestBody List<CheckRequest> requestList){
        if(requestList==null || requestList.size()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }
        boolean b = checkRequestService.saveOrUpdateBatch(requestList);

        //如果添加检查失败直接抛异常，这样以后还能回滚
        if(!b){
            throw new AppException(400001,"添加检查申请失败");
        }

        requestList.forEach(System.out::println);

            List<Cost> costList = new ArrayList<>();
            requestList.forEach(checkRequest -> {
                Cost cost = new Cost(null,checkRequest.getRegisterId(),
                        checkRequest.getMedicalTechnology().getTechName(),
                        checkRequest.getId(),
                        "检查",
                        checkRequest.getMedicalTechnology().getTechPrice(),
                        1,null,0,null,null,null,null);
                costList.add(cost);
            });

            //批量添加费用记录
            boolean b1 = costService.saveBatch(costList);

            //如果添加费用失败抛异常
            if(!b1){
                throw new AppException(400002,"添加费用记录失败");
            }

        return ResultBean.SUCCESS();
    }

    /**
     * 修改检查申请的数据
     * 一个检查申请的记录是三次填写完成的：
     *   1、门诊医生开立检查   填写一部分数据   新增
     *   2、检查医生执行检查   填写一部分数据   修改(check_time,check_state,check_employee_id)
     *   3、结果录入的医生录入检查结果   填写一部分数据  修改(inputcheck_employee_id,check_result,check_state)
     * @param checkRequest
     * @return
     */
    @PutMapping("/update")
    public ResultBean update(@RequestBody CheckRequest checkRequest){
        System.out.println("-------CheckRequestController---update------------");
        System.out.println(checkRequest);
        UpdateWrapper<CheckRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                //执行检查   检查医生编号  检查时间  修改检查状态(执行完成)
                .set(checkRequest.getCheckEmployeeId()!=null,"check_employee_id",checkRequest.getCheckEmployeeId())
                .set(checkRequest.getCheckState()!=null,"check_state",checkRequest.getCheckState())
                .set("执行完成".equals(checkRequest.getCheckState()),"check_time",new Date())

                //检查完成  输入结果医生编号  检查结果  修改状态为(已出结果)
                .set(checkRequest.getInputcheckEmployeeId()!=null,"inputcheck_employee_id",checkRequest.getInputcheckEmployeeId())
                .set(StringUtils.isNotEmpty(checkRequest.getCheckResult()),"check_result",checkRequest.getCheckResult())
                .eq("id",checkRequest.getId());
        boolean update = checkRequestService.update(updateWrapper);
        return update?ResultBean.SUCCESS():ResultBean.ERROR("更新检查申请失败");
    }
}
