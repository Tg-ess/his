package org.example.service;

public interface IVerifyCodeService {

    /*
    生成图片验证码
     */
    String graph(String key);

    /*
    验证码验证
     */
    boolean valide(String key, String code);
}
