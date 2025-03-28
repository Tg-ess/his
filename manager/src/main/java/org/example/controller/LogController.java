package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Employee;
import org.example.service.IEmployeeService;
import org.example.utils.JwtUtils;
import org.example.utils.MD5Utils;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/in")
    public ResultBean login(@RequestBody Employee employee){

        //先使用用户名查询对应的员工
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username",employee.getUsername());

        Employee one = employeeService.getOne(wrapper);

        //查不到报错  用户不存在
        if (one==null){
            return ResultBean.ERROR("用户不存在");
//            throw new AppException(AppExceptionCodeMsg.USERNAME_NOT_EXISTS);
        }

        //在查到的员工对象身上获取数据库中存储的密码   必须先重置密码
        String dbpass = one.getPassword();
        System.out.println(dbpass);

        //解密  第一个参数是从前端传递的密码(明文)   第二个参数是数据库中查到的密码(密文)
        boolean passOK = MD5Utils.decrypt(employee.getPassword(), dbpass);

        //密码不对报错
        if(!passOK){

            return ResultBean.ERROR("密码错误");
//            throw new AppException(AppExceptionCodeMsg.PASSWORD_ERROR);
        }

        //登录成功   要将查到的员工对象携带到前端   为了安全性 将盐值 和 密码置为null
        one.setPassword(null);
        one.setSalt(null);

        /*
        之前的项目中  登录成功要存储到session中
        现在登录验证我们不使用session
        前后端分离的项目中更多使用的是token(就是一个字符串[某种机制生成的，自带超时时间])
        将token给到前端  前端存起来  每次发请求携带上就可以了
         */
        //登录成功生成token
        String token = JwtUtils.generateToken(one);

        //在响应中要携带  登录成功的员工对象(data)  还有 token(msg)
        ResultBean resultBean = ResultBean.SUCCESS(one);
        resultBean.setMsg(token);

        return resultBean;
    }

}
