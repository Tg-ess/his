package org.example.controller;

import org.example.service.IVerifyCodeService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 发送验证码接口
 * 1、图形验证码
 * 2、短信验证码
 * 3、登录随机验证码
 * 4、邮件随机码
 */
@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    @Autowired
    IVerifyCodeService verifyCodeService;

    /**
     * -------------------生成图形验证码---------------------
     * @param key
     * @return
     */
    @GetMapping("/image/{key}")
    public ResultBean graph(@PathVariable("key") String key){
        String base64Str = verifyCodeService.graph(key);
        return ResultBean.SUCCESS(base64Str);
    }

    /**
     * 验证码的验证
     * @param key  生成验证时要保持一致
     * @param code  输入的验证码
     * @return
     */
    @GetMapping("/valide/{key}/{code}")
    public ResultBean valid(@PathVariable("key")String key,@PathVariable("code")String code){
        System.out.println("---------验证码验证--------"+key+"  "+code);
        boolean valide = verifyCodeService.valide(key, code);
        return valide?ResultBean.SUCCESS():ResultBean.ERROR("验证失败");
    }

}
