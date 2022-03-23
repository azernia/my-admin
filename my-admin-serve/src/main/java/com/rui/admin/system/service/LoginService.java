package com.rui.admin.system.service;

import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.request.UserDTO;

/**
 * @author rui
 * @since 2022/3/22 16:07
 */
public interface LoginService {


    /**
     * 执行登录
     * @param userDTO 用户对象
     * @return 登录结果
     */
    RespBean doLogin(UserDTO userDTO);
}
