package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.MedicalRecord;
import org.example.query.MedicalRecordQuery;
import org.example.service.IMedicalRecordService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController extends BaseController<MedicalRecord, MedicalRecordQuery>{

    @Autowired
    IMedicalRecordService medicalRecordService;


    @GetMapping("register/{rid}")
    public ResultBean getByRegisterId(@PathVariable("rid") Integer registerId){
        QueryWrapper<MedicalRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("register_id",registerId);

        MedicalRecord one = medicalRecordService.getOne(queryWrapper);

//        return ResultBean.SUCCESS(one);
        return one == null ? ResultBean.ERROR(AppExceptionCodeMsg.DATA_NOT_FOUND)
                :ResultBean.SUCCESS(one);
    }

}
