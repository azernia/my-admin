package com.rui.admin.commons.utils;

/**
 * @author rui
 * @since 2022/3/1 3:44 PM
 */
public class UserUtils {
    /**
     * 获取当前登录用户ID
     * @return 用户ID
     */
    public static Integer getCurrentUserId() {
        // LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // return loginUser.getHr().getId();
        return 0;
    }
}
