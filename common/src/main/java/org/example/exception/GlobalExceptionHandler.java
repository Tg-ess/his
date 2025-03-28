package org.example.exception;

import org.example.vo.ResultBean;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常处理类
 * 只要发生异常就会拦截
 *  判断是否是自定义的异常  是的话  返回ResultBean的时候填写自己异常的code和msg
 *                          不是   500  服务器内部错误
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResultBean exceptionHandler(Exception e){
        if(e instanceof AppException){
            AppException appException = (AppException)e;
            return ResultBean.ERROR(appException.getCode(),appException.getMsg());
        }
        return ResultBean.ERROR();
    }
}
