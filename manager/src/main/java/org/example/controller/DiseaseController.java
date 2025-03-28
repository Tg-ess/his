package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.pojo.Disease;
import org.example.query.DiseaseQuery;
import org.example.service.IDiseaseService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/disease")
public class DiseaseController extends BaseController<Disease, DiseaseQuery> {

    @Autowired
    IDiseaseService diseaseService;

    /*
       @param disease  传递条件本来应该由DiseaseQuery来进行接收
                       但是传递的条件 Disease对象中都有  就没有创建Query类
     */
    @PostMapping("/conditions")
    public ResultBean getByConditions(@RequestBody Disease disease){
        QueryWrapper<Disease> wrapper = new QueryWrapper<>();
        wrapper.like("disease_name",disease.getDiseaseName())
                .like("disease_code",disease.getDiseaseICD());
        List<Disease> list = diseaseService.list(wrapper);
        return ResultBean.SUCCESS(list);
    }

}
