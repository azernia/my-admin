package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author rui
 * @since 2022/3/22 16:08
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public RespBean doLogin(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (ObjectUtil.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // TODO 生成 token
        // TODO 存入 Redis
        return RespBean.success("登录成功");
    }
}
