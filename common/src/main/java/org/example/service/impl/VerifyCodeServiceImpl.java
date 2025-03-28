package org.example.service.impl;

import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.service.IVerifyCodeService;
import org.example.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 生成图片验证码
     * @param key
     * @return
     */
    @Override
    public String graph(String key) {

        //生成四位验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //存放到redis中
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);;

        //把验证码的值合并到图片
        //把图片使用Base64编码
        //返回base64编码的字符串
        try{
            String base64Str = VerifyCodeUtils.VerifyCode(140, 40, code);
            base64Str = base64Str.replaceAll("\r\n","");
            return "data:image/jpg;base64,"+base64Str;
        }catch (Exception e){
            e.printStackTrace();
            throw new AppException(AppExceptionCodeMsg.GENERATE_GRAPH_VERIFYCODE_FAIL);
        }
    }

    @Override
    public boolean valide(String key, String code) {

        //通过key[和生成时要保持一致] 获取redis中存储的验证码
        Object o = redisTemplate.opsForValue().get(key);

        //如果获取不到  报超时异常
        if(o == null){
            //在测试的时候可以抛异常，这样就能看到到底哪里出了问题，真正在运行的时候最好不要抛异常
            //让前端显示一个错误信息即可
//            throw new AppException(AppExceptionCodeMsg.CODE_TIMEOUT);

            return false;
        }

        System.out.println("获取验证码------------"+key+"--------"+o.toString());

        //判断redis中获取的验证码和传递过来的验证码是否一致
        if(!o.toString().equalsIgnoreCase(code)){

            //在测试的时候可以抛异常，这样就能看到到底哪里出了问题，真正在运行的时候最好不要抛异常
            //让前端显示一个错误信息即可
//            throw new AppException(AppExceptionCodeMsg.INVALID_CODE);

            return false;
        }

        return true;
    }
}
