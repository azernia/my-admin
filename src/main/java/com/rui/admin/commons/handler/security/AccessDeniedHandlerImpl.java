package com.rui.admin.commons.handler.security;

import com.alibaba.fastjson.JSON;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        log.error("访问禁止异常{}", e);
        RespBean fail = RespBean.fail("登录失败");
        WebUtils.renderString(resp, JSON.toJSONString(fail));
    }
}
