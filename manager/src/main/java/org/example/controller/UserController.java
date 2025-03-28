package org.example.controller;


import io.swagger.annotations.Api;
import org.example.pojo.User;
import org.example.query.UserQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyx
 * @since 2024-12-19
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserQuery>{

}
