package com.rui.admin.commons.utils;

import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 二次封装 Hutool JwtUtil
 * @author rui
 * @since 2022/3/23 14:29
 */
public class SecondaryJwtUtils {

    public static final String KEY = "rui";

    private SecondaryJwtUtils() {}

    public static String createJwtToken(Integer userId) {
        LocalDateTime now = LocalDateTime.now();
        // 有效时间 2H
        LocalDateTime newTime = now.plusHours(2);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put("userId", userId);
        return JWTUtil.createToken(payload, KEY.getBytes(StandardCharsets.UTF_8));
    }

}
