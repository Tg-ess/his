package org.example.interceptor;

import io.jsonwebtoken.Claims;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }

        String token = request.getHeader("token");
        System.out.println("-------登录拦截------"+token);

        if(token == null || token.length()==0){

            throw new AppException(AppExceptionCodeMsg.NO_LOGIN);
        }

        Claims claims = JwtUtils.getClaimsVyToken(token);
        Object name = claims.get("name");
        System.out.println("获取到name-------"+name);

        if(name==null){
            throw new AppException(AppExceptionCodeMsg.NO_LOGIN);
        }

        return true;
    }
}
