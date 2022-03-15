package com.rui.admin.commons.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private BeanCopyUtils() {

    }

    /**
     * 复制单个对象
     * @param source 原对象
     * @param clazz 目标对象
     * @param <V> 泛型
     * @return 返回拷贝的目标对象
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            // 实现属性 Copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <S,V> List<V> copyBeanList(List<S> list, Class<V> clazz) {
        return list.stream().map(source -> copyBean(source, clazz)).collect(Collectors.toList());
    }

}
