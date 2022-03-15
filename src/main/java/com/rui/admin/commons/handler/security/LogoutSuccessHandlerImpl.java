package com.rui.admin.commons.handler.security;

import com.alibaba.fastjson.JSON;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理器
 * @author rui
 * @date 2022/2/12 9:44 AM
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        RespBean success = RespBean.success("退出成功");
        WebUtils.renderString(response, JSON.toJSONString(success));
    }
}
