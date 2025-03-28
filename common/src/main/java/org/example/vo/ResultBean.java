package org.example.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.exception.AppExceptionCodeMsg;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean {

    /*
    自己拟定
    200 success
    500 error
        10001 user not found
        10002 password fail
        30001 params is null
     */
    private Integer code;
    private String msg;
    private Object data;

    public ResultBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultBean SUCCESS() {
        return new ResultBean(200,"success",null);
    }

    public static ResultBean SUCCESS(Object data) {
        return new ResultBean(200,"success",data);
    }

    public static ResultBean ERROR() {
        return new ResultBean(500,"error");
    }

    public static ResultBean ERROR(Integer code, String msg) {
        return new ResultBean(code,msg);
    }

    public static ResultBean ERROR(String msg) {
        return new ResultBean(500,msg);
    }

    public static ResultBean ERROR(AppExceptionCodeMsg codeMsg) {
        return new ResultBean(codeMsg.getCode(), codeMsg.getMsg());
    }
}
