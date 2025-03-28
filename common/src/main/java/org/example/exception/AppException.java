package org.example.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * extends RuntimeException  运行时异常不需要处理
 */
@Data
@NoArgsConstructor
public class AppException extends RuntimeException{

    private Integer code = 500;
    private String msg = "服务器内部错误";

    public AppException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public AppException(AppExceptionCodeMsg codeMsg){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }




}
