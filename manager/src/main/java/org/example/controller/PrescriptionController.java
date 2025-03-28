package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Cost;
import org.example.pojo.Prescription;
import org.example.query.PrescriptionQuery;
import org.example.service.ICostService;
import org.example.service.IPrescriptionService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/prescription")
public class PrescriptionController extends BaseController<Prescription,PrescriptionQuery>{

    @Autowired
    IPrescriptionService prescriptionService;

    @PostMapping("/conditions")
    public ResultBean getByConditions(@RequestBody PrescriptionQuery query){
        List<Prescription> list = prescriptionService.list(query.getJoinQueryWrapper());
        return ResultBean.SUCCESS(list);
    }

    @Autowired
    ICostService costService;

    /**
     * 添加处方时同时添加费用
     * @param prescriptions
     * @return
     */
    @Transactional
    @PostMapping("/addbatch")
    public ResultBean addbatch(@RequestBody List<Prescription> prescriptions){

        if (prescriptions==null || prescriptions.size()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        boolean b = prescriptionService.saveBatch(prescriptions);

        if (!b){
            throw new AppException(600001,"开药失败");
        }


        //添加费用
        List<Cost> costs = new ArrayList<>();
        //遍历处方
        prescriptions.forEach(prescription -> {
            //一个处方 对应一个费用
            Cost cost = new Cost(null, prescription.getRegisterId(),
                    prescription.getDrugInfo().getDrugName(),
                    prescription.getId(),
                    "药品", prescription.getDrugInfo().getDrugPrice(),
                    prescription.getDrugNumber(), null, 0, null, null, null, null);
            costs.add(cost);
        });

        boolean b1 = costService.saveBatch(costs);

        if(!b1){
            throw new AppException(600002,"开药费用添加失败");
        }

        return ResultBean.SUCCESS();
    }

    @ApiOperation("批量修改发药状态")
    @PutMapping("/updatestate/{state}")
    public ResultBean updatestate(@PathVariable("state")String state,@RequestBody List<Integer> ids){
        //update prescription drug_state='已发药' where id in (1,2,3,4)
        UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("drug_state",state)
                .in("id",ids);

        boolean update = prescriptionService.update(updateWrapper);
        return update?ResultBean.SUCCESS():ResultBean.ERROR("修改处方状态失败");
    }

}
