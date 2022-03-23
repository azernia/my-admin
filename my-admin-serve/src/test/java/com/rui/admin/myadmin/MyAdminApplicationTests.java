package com.rui.admin.myadmin;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.model.entity.Role;
import com.rui.admin.system.model.entity.RoleMenu;
import com.rui.admin.system.model.entity.RoleUser;
import com.rui.admin.system.service.MenuService;
import com.rui.admin.system.service.RoleMenuService;
import com.rui.admin.system.service.RoleService;
import com.rui.admin.system.service.RoleUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MyAdminApplicationTests {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleUserService roleUserService;

    @Test
    void contextLoads() {
    }

    @Test
    public void jwtTokenTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newTime = now.plusHours(2);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put("userId", 1);
        String key = "rui";
        String token = JWTUtil.createToken(payload, key.getBytes(StandardCharsets.UTF_8));
        System.out.println(token);
        if (JWTUtil.verify(token, key.getBytes(StandardCharsets.UTF_8))) {
            JWT jwt = JWTUtil.parseToken(token);
            Integer userId = (Integer) jwt.getPayload("userId");
            System.out.println(userId);
        }
    }


}
