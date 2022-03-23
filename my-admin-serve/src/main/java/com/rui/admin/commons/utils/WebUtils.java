package com.rui.admin.commons.utils;

import cn.hutool.core.util.ObjectUtil;
import com.rui.admin.commons.enums.ServerExceptionEnum;
import com.rui.admin.commons.exception.BusinessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 获取 request 对象
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes)) {
            throw new BusinessException(ServerExceptionEnum.REQUEST_EMPTY.getDesc());
        }
        return requestAttributes.getRequest();
    }

    /**
     * 获取 response 对象
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes)) {
            throw new BusinessException(ServerExceptionEnum.REQUEST_EMPTY.getDesc());
        }
        return requestAttributes.getResponse();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
