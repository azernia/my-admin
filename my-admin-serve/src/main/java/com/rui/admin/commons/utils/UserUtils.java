package com.rui.admin.commons.utils;

import com.rui.admin.config.security.entity.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author rui
 * @since 2022/3/1 3:44 PM
 */
public class UserUtils {

    private UserUtils() {}

    /**
     * 获取当前登录用户ID
     * @return 用户ID
     */
    public static Integer getCurrentUserId() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getUser().getId();
    }
}
