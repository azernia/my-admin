package com.rui.admin.commons.enums;

import com.rui.admin.commons.specification.NormalEnumSpecification;

public enum ServerExceptionEnum implements NormalEnumSpecification {
    REQUEST_EMPTY(1, "当前请求参数为空或数据缺失，请联系管理员"),

    NOT_EXIST(2, "对象不存在")
    ;

    private final Integer code;

    private final String desc;

    ServerExceptionEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
