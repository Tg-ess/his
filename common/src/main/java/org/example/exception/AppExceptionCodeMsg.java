package org.example.exception;

public enum AppExceptionCodeMsg {

    INVALID_CODE(10000,"无效的验证码"),
    CODE_TIMEOUT(10004,"验证码超时"),
    USERNAME_NOT_EXISTS(10001,"用户名不存在"),
    PASSWORD_ERROR(10002,"密码错误"),
    USERNAME_OR_PASSWORD_ERROR(10002,"用户名或密码错误"),
    PARAMS_EMPTY(10003,"请求参数为空"),
    DELETE_FAIL(20000,"删除失败"),
    UPDATE_FAIL(20002,"更新失败"),
    INSERT_FAIL(20003,"添加失败"),
    SAVE_FAIL(20004,"保存失败"),
    DATA_NOT_FOUND(20001,"对象未找到"),
    GENERATE_VERIFYCODE_FAIL(30001,"生成验证码失败"),
    GENERATE_GRAPH_VERIFYCODE_FAIL(30002,"图片验证码生成失败"),
    NO_LOGIN(50001,"未登录")
    ;

    private Integer code;
    private String msg;

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    AppExceptionCodeMsg(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
