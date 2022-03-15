package com.rui.admin.commons.handler.security;

import com.alibaba.fastjson.JSON;
import com.rui.admin.commons.constants.RespConstant;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) {
        log.error("认证异常()", e);
        RespBean fail;
        if(e instanceof BadCredentialsException) {
            fail = RespBean.fail("登录失败");
        } else if(e instanceof InsufficientAuthenticationException) {
            fail = RespBean.fail(RespConstant.NEED_LOGIN, "需要重新登录");
        } else if(e instanceof LockedException) {
            fail = RespBean.fail("账户被锁定，请联系管理员");
        } else if(e instanceof CredentialsExpiredException) {
            fail = RespBean.fail("密码过期，请联系管理员");
        } else if(e instanceof AccountExpiredException) {
            fail = RespBean.fail("账户过期，请联系管理员");
        }else if(e instanceof DisabledException) {
            fail = RespBean.fail("账户被禁用，请联系管理员");
        } else {
            fail = RespBean.fail("认证或授权失败");
        }
        WebUtils.renderString(resp, JSON.toJSONString(fail));
    }
}
