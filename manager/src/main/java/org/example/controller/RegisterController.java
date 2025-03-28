package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Department;
import org.example.pojo.Register;
import org.example.query.RegisterQuery;
import org.example.service.IRegisterService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/register")
public class RegisterController extends BaseController<Register, RegisterQuery> {

    @Autowired
    IRegisterService registerService;

    @ApiOperation(value = "修改挂号状态")
    @PutMapping("/change/state")
    public ResultBean changeState(@RequestBody Register register){

        if(register==null ||
                register.getVisitState()==null ||
                register.getId()==null ||
                register.getId()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        UpdateWrapper<Register> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("visit_state",register.getVisitState())
                .eq("id",register.getId());

        boolean update = registerService.update(updateWrapper);

        return update?ResultBean.SUCCESS():ResultBean.ERROR("修改挂号状态失败");
    }

    @ApiOperation(value = "获取已用号别")
    @GetMapping("/count/{employeeId}")
    public ResultBean getCount(@PathVariable("employeeId") Integer employeeId){
        System.out.println("RegisterController--------getCount");
        if(employeeId == null || employeeId == 0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }
        RegisterQuery query = new RegisterQuery();
        query.setEmployeeId(employeeId);
        List<Register> registerList = registerService.list(query.getByCondition());
        System.out.println(registerList);
        return ResultBean.SUCCESS(registerList.size());
    }

    @ApiOperation("根据病历号查询患者信息")
    @PostMapping("/case/{casenumber}")
    public ResultBean getByConditions(@PathVariable("casenumber")String caseNumber){
        QueryWrapper<Register> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("case_number",caseNumber);

        Register one = registerService.getOne(queryWrapper);
        return one==null?ResultBean.ERROR("患者不存在"):ResultBean.SUCCESS(one);
    }

    @ApiOperation("批量删除")
    @PostMapping("/batch1/{ids}")   // post + /xxx/batch
    public ResultBean batchDel(@PathVariable("ids") List<Integer> ids){
        System.out.println("----------RegisterController---------batchDel---------------");
        boolean b = baseService.removeBatchByIds(ids);
        if (!b){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.DELETE_FAIL);
        }
        return ResultBean.SUCCESS();
    }



}
