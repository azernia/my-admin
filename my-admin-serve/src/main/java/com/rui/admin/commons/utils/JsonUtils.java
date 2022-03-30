package com.rui.admin.commons.utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rui
 * @since 2022/3/30 11:51
 */
public class JsonUtils {
    private JsonUtils() {}

    public static <T> List<T> json2Array(String json, Class<T> tClass) {
        List<T> result = null;
        try {
            result = JSON.parseArray(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ObjectUtil.isNull(result)) {
            result = new ArrayList<>();
        }
        return result;
    }
}
