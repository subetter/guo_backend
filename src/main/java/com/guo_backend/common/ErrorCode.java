package com.guo_backend.common;

/**
 * 全局错误码
 *
 * @author ame
 */
public enum ErrorCode {

    SUCCESS(0, "success"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NULL_ERROR(400001, "请求数据为空"),
    NOT_LOGIN(40100, "未登录"),
    NO_AUTH(40101, "无权限"),
    //屏蔽了所有的服务器的报错
    SYSTEM_ERROR(50000, "系统内部异常");


    private final int code;

    /**
     * 状态码信息
     */
    private final String message;



    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
