package com.rui.admin.commons.exception;

/**
 * @author rui
 * @date 2022/2/10 9:22 AM
 */
public class BusinessException extends RuntimeException {

    private Integer code = 500;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "服务器错误";
        }
        return message;
    }
}
