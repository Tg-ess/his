package org.example.controller;


import org.example.pojo.SettleCategory;
import org.example.query.SettleCategoryQuery;
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
@RequestMapping("/settlecategory")
public class SettleCategoryController extends BaseController<SettleCategory, SettleCategoryQuery>{

}
