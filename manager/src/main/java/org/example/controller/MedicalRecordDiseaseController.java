package org.example.controller;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Disease;
import org.example.pojo.MedicalRecordDisease;
import org.example.query.RecordDiseaseQuery;
import org.example.service.IMedicalRecordDiseaseService;
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
@RequestMapping("/recorddisease")
public class MedicalRecordDiseaseController extends BaseController<MedicalRecordDisease, RecordDiseaseQuery>{

    @Autowired
    IMedicalRecordDiseaseService service;

    /**
     * 通过 病历id查询对应疾病
     * @param mrid
     * @return
     */
    @GetMapping("/mrid/{mrid}")
    public ResultBean getByMrId(@PathVariable("mrid") Integer mrid){
        MPJLambdaWrapper<MedicalRecordDisease> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("mrd")
                .selectAll(MedicalRecordDisease.class,"mrd")
                .selectAs(Disease::getDiseaseName,"'disease.diseaseName'")
                .selectAs(Disease::getDiseaseICD,"'disease.diseaseICD'")
                .leftJoin(Disease.class,"d",Disease::getId,MedicalRecordDisease::getDiseaseId)
                .eq("mrd.medical_record_id",mrid);

        List<MedicalRecordDisease> list = service.list(wrapper);
        return ResultBean.SUCCESS(list);
    }

    @PostMapping("/addbatch")
    public ResultBean addbatch(@RequestBody List<MedicalRecordDisease> medicalRecordDiseases){

        if (medicalRecordDiseases==null || medicalRecordDiseases.size()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        boolean b = service.saveBatch(medicalRecordDiseases);
        return ResultBean.SUCCESS();
    }

    @DeleteMapping("/del/{mrId}/{dId}")
    public ResultBean delByTwoId(@PathVariable("mrId") Integer mrId, @PathVariable("dId") Integer dId){

        RecordDiseaseQuery query = new RecordDiseaseQuery();
        query.setMedicalRecordId(mrId);
        query.setDiseaseId(dId);

        boolean b = service.remove(query.getQueryWrapper());

        return b?ResultBean.SUCCESS():ResultBean.ERROR("删除失败");
    }

}
