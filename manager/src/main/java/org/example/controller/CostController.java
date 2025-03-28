package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.CheckRequest;
import org.example.pojo.Cost;
import org.example.pojo.Prescription;
import org.example.query.CostQuery;
import org.example.query.RecordDiseaseQuery;
import org.example.service.ICheckRequestService;
import org.example.service.ICostService;
import org.example.service.IPrescriptionService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost")
public class CostController extends BaseController<Cost, CostQuery>{

    @Autowired
    ICostService costService;

    @Autowired
    ICheckRequestService checkRequestService;

    @Autowired
    IPrescriptionService prescriptionService;

    @Override
    @DeleteMapping("/del/{id}")
    public ResultBean del(@PathVariable("id") Integer id) {
        if(id == null){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        Cost cost = costService.getOne(new QueryWrapper<Cost>().eq("id",id));
        String costtype = cost.getCosttype();
        if("检查".equals(costtype)){
            boolean del = checkRequestService.removeById(cost.getRefId());

            if(!del){
                throw new AppException(70002,"删除检查申请失败");
            }
        }else if("药品".equals(costtype)){

            boolean del = prescriptionService.removeById(cost.getRefId());
            if(!del){
                throw new AppException(500004,"删除处方失败");
            }
        }

        boolean delete = costService.removeById(id);

        if(!delete){
            throw new AppException(70001,"删除失败");
        }

        return ResultBean.SUCCESS();
    }

    @Transactional
    @PostMapping("/refund")
    public ResultBean refund(@RequestBody CostQuery costQuery){

        if(costQuery==null||costQuery.getState()==null){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        boolean update = costService.update(costQuery.getRefundWrapper());

        if(!update){
            throw new AppException(60001,"退费失败");
        }

            Cost cost = costService.getOne(new QueryWrapper<Cost>().eq("id",costQuery.getId()));
            String costtype = cost.getCosttype();
            if("检查".equals(costtype)){
                UpdateWrapper<CheckRequest> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("check_state","已退费")
                        .eq("id",cost.getRefId());
                boolean up = checkRequestService.update(updateWrapper);

                if(!up){
                    throw new AppException(60002,"更新检查申请状态失败");
                }
            }else if("药品".equals(costtype)){
                UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("drug_state","已退费")
                        .eq("id",cost.getRefId());
                boolean up = prescriptionService.update(updateWrapper);
                if(!up){
                    throw new AppException(400004,"更新药品缴费状态失败");
                }
            }

        return ResultBean.SUCCESS();


    }

    @Transactional
    @PostMapping("/pay")
    public ResultBean pay(@RequestBody CostQuery costQuery){

        if(costQuery==null||
                StringUtils.isEmpty(costQuery.getPayMethod())||
                costQuery.getSettleCategoryId()==null){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        boolean update = costService.update(costQuery.getUpdateWrapper());

        if(!update){
            throw new AppException(50001,"支付失败");
        }

        costQuery.getIds().forEach(costId->{
            Cost cost = costService.getOne(new QueryWrapper<Cost>().eq("id",costId));
            String costtype = cost.getCosttype();
            if("检查".equals(costtype)){
                UpdateWrapper<CheckRequest> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("check_state","已缴费")
                        .eq("id",cost.getRefId());
                boolean up = checkRequestService.update(updateWrapper);

                if(!up){
                    throw new AppException(50002,"更新检查申请状态失败");
                }
            }else if("药品".equals(costtype)){
                UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("drug_state","已缴费")
                        .eq("id",cost.getRefId());
                boolean up = prescriptionService.update(updateWrapper);
                if(!up){
                    throw new AppException(300004,"更新药品缴费状态失败");
                }
            }
        });

        return ResultBean.SUCCESS();
    }

    @PostMapping("/conditions")
    public ResultBean getByConditions(@RequestBody CostQuery costQuery){
        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("register_id",costQuery.getRegisterId());

        List<Cost> list = costService.list(queryWrapper);
        return ResultBean.SUCCESS(list);
    }

    @PostMapping("/{rId}/{refId}")
    public ResultBean getByTwoId(@PathVariable("rId") Integer rId, @PathVariable("refId") Integer refId){
        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("register_id",rId);
        queryWrapper.eq("ref_id",refId);
        List<Cost> list = costService.list(queryWrapper);
        return ResultBean.SUCCESS(list);
    }

    @DeleteMapping("/del/{rId}/{refId}")
    public ResultBean delByTwoId(@PathVariable("rId") Integer rId, @PathVariable("refId") Integer refId){

        QueryWrapper<Cost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("register_id",rId);
        queryWrapper.eq("ref_id",refId);

        boolean b = costService.remove(queryWrapper);

        return b?ResultBean.SUCCESS():ResultBean.ERROR("删除失败");
    }

}
