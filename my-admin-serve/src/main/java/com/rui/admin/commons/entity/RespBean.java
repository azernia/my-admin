package com.rui.admin.commons.entity;

import com.rui.admin.commons.constants.RespConstant;

public class RespBean {
    private Integer code;
    private String msg;
    private Object data;

    public static RespBean success() {
        return new RespBean(RespConstant.SUCCESS_CODE, "操作成功", null);
    }

    public static RespBean success(String msg) {
        return new RespBean(RespConstant.SUCCESS_CODE, msg, null);
    }

    public static RespBean success(Object data) {
        return new RespBean(RespConstant.SUCCESS_CODE, null, data);
    }

    public static RespBean success(String msg, Object data) {
        return new RespBean(RespConstant.SUCCESS_CODE, msg, data);
    }

    public static RespBean fail(String msg) {
        return new RespBean(RespConstant.ERROR_CODE, msg, null);
    }

    public static RespBean fail(Integer code, String msg) {
        return new RespBean(code, msg, null);
    }

    private RespBean() {
    }

    private RespBean(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
