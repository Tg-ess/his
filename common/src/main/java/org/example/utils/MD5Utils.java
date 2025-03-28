package org.example.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Utils {

    /**
     * 加盐加密
     * @param password  初始密码
     * @return          加盐密码
     */
    public static String encrypt(String password) {
        // 1.生成随机盐值
        // 通过UUID生成唯一的数字作为随机盐值
        String salt = UUID.randomUUID().toString().replace("-","");// 顺便去掉 -
        // 2.根据初始密码 和 随机盐值 通过md5生成 加盐加密的密码
        // StandardCharsets.UTF_8  设置编码格式
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes(StandardCharsets.UTF_8));
        // 3.将盐值 和 加盐加密得到的密码一起返回(合并盐值和加盐密码)
        return salt +"$"+finalPassword;
    }
    /**
     * 加盐加密的方法重载
     * @param password  初始密码
     * @return          加盐密码
     */
    public static String encrypt(String password,String salt) {

        // 2.根据初始密码 和 随机盐值 通过md5生成 加盐加密的密码
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes(StandardCharsets.UTF_8));
        // 3.将盐值 和 加盐加密得到的密码一起返回(合并盐值和加盐密码)
        // 这里的"$"是自定义的拼接规则，用于区分随机盐值和明文密码
        return salt +"$"+finalPassword;
    }
    /**
     * 加盐解密
     * @param password  初始密码
     * @param dbPassword 数据库中的密码
     * @return
     */
    public static boolean decrypt(String password,String dbPassword) {
        // 先进行参数判断
        // StringUtils.hasLength() 是Spring提供的可以判断传入的字符串是否为空和长度是否为0
        // 判端长度是否为65是因为：随机盐值是32位，通过md5生成的密码也是32位 $ 长度1位
        if (StringUtils.hasLength(password) && StringUtils.hasLength(dbPassword)
                && dbPassword.length() == 65 && dbPassword.contains("$")) {
            // 分割数据库中的密码
            String[] arr = dbPassword.split("\\$");
            // 得到盐值
            String salt = arr[0];
            // 将盐值和初始密码传入重载的加盐加密的方法，生成新的密码
            String finalPassword = encrypt(password, salt);
            // 比较待验证的密码和明文密码是否相等
            if (finalPassword.equals(dbPassword)) {
                return true;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        //将明文加密  0b44ea9d7cdd43808632b17b3db7b41d$59cc22b88303e5cd790ffc90ef895714
//        String encrypt = MD5Utils.encrypt("123456");
//        System.out.println(encrypt);

        /*
        解密的过程：
          1、将传入的明文采用同样的方式加密
             使用到之前的盐值   0b44ea9d7cdd43808632b17b3db7b41d$59cc22b88303e5cd790ffc90ef895714
             之前的密文是  盐+$+密文
          2、和之前加密的密码进行对比
         */
        boolean decrypt = MD5Utils.decrypt("12345", "0b44ea9d7cdd43808632b17b3db7b41d$59cc22b88303e5cd790ffc90ef895714");
        System.out.println(decrypt);
    }
}
