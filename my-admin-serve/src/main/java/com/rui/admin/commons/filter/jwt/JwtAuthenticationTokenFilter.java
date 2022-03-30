package com.rui.admin.commons.filter.jwt;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.rui.admin.commons.constants.RedisConstant;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.commons.utils.RedisCacheUtils;
import com.rui.admin.commons.utils.SecondaryJwtUtils;
import com.rui.admin.config.security.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author rui
 * @since 2022/3/23 14:38
 */
// OncePerRequestFilter 继承该类保证至少经过一次该过滤器
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (ObjectUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        Integer userId;
        if (JWTUtil.verify(token, SecondaryJwtUtils.KEY.getBytes(StandardCharsets.UTF_8))) {
            userId = (Integer) JWTUtil.parseToken(token).getPayload("userId");
        } else {
            log.info("==========>token 非法");
            throw new BusinessException("token 非法");
        }
        LoginUser loginUser = redisCacheUtils.getCacheObject(RedisConstant.LOGIN + userId);
        if (ObjectUtil.isNull(loginUser)) {
            log.info("==========>用户尚未登录");
            throw new BusinessException("用户尚未登录");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        // 存入 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
