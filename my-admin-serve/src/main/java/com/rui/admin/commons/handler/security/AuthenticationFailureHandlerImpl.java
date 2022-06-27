package com.rui.admin.commons.handler.security;

import com.alibaba.fastjson.JSON;
import com.rui.admin.commons.constants.RespConstants;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rui
 * @since 2022/3/2 10:36 AM
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) {
        log.error("授权失败()", e);
        RespBean fail;
        if (e instanceof LockedException) {
            fail = RespBean.fail("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            fail = RespBean.fail("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            fail = RespBean.fail("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            fail = RespBean.fail(RespConstants.LOCKED,"账户被禁用，请联系管理员!");
        } else if (e instanceof BadCredentialsException) {
            fail = RespBean.fail("用户名或者密码输入错误，请重新输入!");
        } else {
            fail = RespBean.fail("认证或授权失败");
        }
        WebUtils.renderString(resp, JSON.toJSONString(fail));
    }
}
