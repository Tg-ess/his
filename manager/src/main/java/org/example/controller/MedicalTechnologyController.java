package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.pojo.MedicalTechnology;
import org.example.query.MedicalTechnologyQuery;
import org.example.service.IMedicalTechnologyService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/medicaltech")
public class MedicalTechnologyController extends BaseController<MedicalTechnology, MedicalTechnologyQuery> {

    @Autowired
    IMedicalTechnologyService medicalTechnologyService;

    @GetMapping("/type/{type}/{name}")
    public ResultBean getByType(@PathVariable("type") String type,@PathVariable("name")String name){
        QueryWrapper<MedicalTechnology> queryWrapper = new QueryWrapper<MedicalTechnology>()
                .eq("tech_type",type)
                .like(StringUtils.isNotEmpty(name),"tech_name",name);
        List<MedicalTechnology> list = medicalTechnologyService.list(queryWrapper);
        return ResultBean.SUCCESS(list);
    }

}
