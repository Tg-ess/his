package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.pojo.DrugInfo;
import org.example.query.DrugInfoQuery;
import org.example.service.IDrugInfoService;
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
@RequestMapping("/drug")
public class DrugInfoController extends BaseController<DrugInfo, DrugInfoQuery>{

    @Autowired
    IDrugInfoService drugInfoService;

    @PostMapping("/conditions")
    public ResultBean getByConditions(@RequestBody DrugInfo drugInfo){

        QueryWrapper<DrugInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("drug_name",drugInfo.getDrugName());

        List<DrugInfo> list = drugInfoService.list(queryWrapper);
        return ResultBean.SUCCESS(list);
    }

}
