package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.RedisCacheUtils;
import com.rui.admin.commons.utils.SecondaryJwtUtils;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.request.UserDTO;
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

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Override
    public RespBean doLogin(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (ObjectUtil.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        String jwtToken = SecondaryJwtUtils.createJwtToken(userId);
        // 存入 Redis
        redisCacheUtils.setCacheObject("login:"+userId, loginUser);
        return RespBean.success("登录成功", jwtToken);
    }
}
