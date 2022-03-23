package com.rui.admin.commons.utils;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author rui
 * @since 2022/3/23 09:29
 */
public class PageUtils {
    /**
     * 第几页
     */
    private static final String PAGE_NO = "page";

    /**
     * 每页多少条
     */
    private static final String PAGE_SIZE = "limit";

    private PageUtils() {}

    public static <T> Page<T> defaultPage() {
        int page = 1;
        int limit = 10;
        String pageNo = WebUtils.getRequest().getParameter(PAGE_NO);
        if (ObjectUtil.isNotEmpty(pageNo)) {
            page = Integer.parseInt(pageNo);
        }
        String pageSize = WebUtils.getRequest().getParameter(PAGE_SIZE);
        if (ObjectUtil.isNotEmpty(pageSize)) {
            limit = Integer.parseInt(pageSize);
        }
        return new Page<>(page, limit);
    }
}
