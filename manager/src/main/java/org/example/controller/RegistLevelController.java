package org.example.controller;


import org.example.pojo.RegistLevel;
import org.example.query.RegistLevelQuery;
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
@RequestMapping("/registlevel")
public class RegistLevelController extends BaseController<RegistLevel, RegistLevelQuery>{

}
