package com.rui.admin.commons.specification;

/**
 * 普通枚举类型规范
 */
public interface NormalEnumSpecification {

    /**
     * 获取枚举编码
     * @return 编号 {@link Integer}
     */
    Integer getCode();

    /**
     * 获取枚举描述
     * @return 描述 {@link String}
     */
    String getDesc();
}
