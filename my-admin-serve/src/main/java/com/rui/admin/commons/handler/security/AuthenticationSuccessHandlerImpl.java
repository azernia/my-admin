package com.rui.admin.commons.handler.security;

import com.alibaba.fastjson.JSON;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.commons.utils.WebUtils;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 * @author rui
 * @date 2022/2/12 9:40 AM
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String s = JSON.toJSONString(RespBean.success("登录成功", BeanCopyUtils.copyBean(loginUser.getUser(), User.class)));
        WebUtils.renderString(response, s);
    }
}
