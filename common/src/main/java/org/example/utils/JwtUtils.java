package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.pojo.Employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //定义密钥(也可以放在配置springboot配置文件中或服务器环境变量中)
    private static final String SHARK = "abcdefghigklmnopqrstuvwxyz123456";

    //生成token
    public static String generateToken(Employee employee) {
        int expire = 360000;  //过期时间为1小时

        //设置自定义的内容
        Map<String, Object> map = new HashMap<>();
        map.put("stuId", String.valueOf(employee.getId()));
        map.put("name", employee.getRealname());
        map.put("salt", employee.getSalt());

//        ObjectMapper objectMapper = new ObjectMapper();
//        String subject = null;
//        try {
//            subject = objectMapper.writeValueAsString(map);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
        return Jwts.builder()
//                .setHeaderParam("type", "JWT")
//                .setSubject(subject)
//                .setIssuedAt(new Date())
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * expire)) //设置过期时间
                .signWith(SignatureAlgorithm.HS512, SHARK)  //采取HS512加密算法
                .compact();
    }

    //解析token
    public static Claims getClaimsVyToken(String token) {
        return Jwts.parser()
                .setSigningKey(SHARK)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setRealname("张三");
//        employee.setSalt("3783218931");
//        String token = JwtUtils.generateToken(employee);
//        System.out.println(token);

        Claims claimsVyToken = JwtUtils.getClaimsVyToken("eyJhbGciOiJIUzUxMiJ9.eyJzYWx0IjoiMzc4MzIxODkzMSIsInN0dUlkIjoiMSIsIm5hbWUiOiLlvKDkuIkiLCJleHAiOjE3MzUyMjM3MTJ9.mktoe6a35uDFvQJOlCyknv0DRcYrvLDj1tVSFoeYjm0lbr1BMp6JfIvY8p92574aWfFDMERMnlTTeSNUtFgZpg");
        System.out.println(claimsVyToken.get("stuId"));
        System.out.println(claimsVyToken.get("name"));
        System.out.println(claimsVyToken.get("salt"));
    }
}