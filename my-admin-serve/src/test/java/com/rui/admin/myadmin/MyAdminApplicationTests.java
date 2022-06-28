package com.rui.admin.myadmin;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.rui.admin.system.model.entity.Client;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.service.ClientService;
import com.rui.admin.system.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class MyAdminApplicationTests {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveClient() {
        Client client = new Client();
        client.setClientId("rui");
        client.setClientSecret(passwordEncoder.encode("rui_web_app"));
        client.setScope("web_app");
        client.setAuthorizedGrantTypes("password,refresh_token,authorization_code,implicit");
        client.setAccessTokenValiditySeconds(7200);
        client.setRefreshTokenValiditySeconds(36000);
        clientService.save(client);
    }

    @Test
    public void redisTest() {
        Boolean delete = redisTemplate.delete("login:menus");
        System.out.println(delete);
    }

    @Test
    public void addMenu() {
        Menu menu = new Menu();
        menu.setName("系统菜单");
        menu.setComponent("menu");
        menu.setComponentName("Menu");
        menu.setAuthority("get:/rui/admin/menu/list");
        menu.setIcon("tree");
        menu.setParentId(1);
        menuService.save(menu);
    }

    @Test
    public void jwtTokenTest() {
        // LocalDateTime now = LocalDateTime.now();
        // LocalDateTime newTime = now.plusSeconds(2);
        // Map<String, Object> payload = new HashMap<>();
        // // 签发时间
        // payload.put(JWTPayload.ISSUED_AT, now);
        // // 过期时间
        // payload.put(JWTPayload.EXPIRES_AT, newTime);
        // // 生效时间
        // payload.put(JWTPayload.NOT_BEFORE, now);
        // payload.put("userId", 1);
        String key = "rui";
        // String token = JWTUtil.createToken(payload, key.getBytes(StandardCharsets.UTF_8));
        // System.out.println(token);
        // if (JWTUtil.verify(token, key.getBytes(StandardCharsets.UTF_8))) {
        //     JWT jwt = JWTUtil.parseToken(token);
        //     Integer userId = (Integer) jwt.getPayload("userId");
        //     System.out.println(userId);
        // }
        String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2NDg3MTcyMTIsImV4cCI6MTY0ODcxNzIxNCwiaWF0IjoxNjQ4NzE3MjEyLCJ1c2VySWQiOjF9.ZWUkeBf9GZE5fSrItv22p6xgvdogW8CCG4dOH0a74Fw";
        if (JWTUtil.verify(testToken, key.getBytes(StandardCharsets.UTF_8))) {
            try {
                JWTValidator.of(testToken).validateDate();
                JWT jwt = JWTUtil.parseToken(testToken);
                Integer userId = (Integer) jwt.getPayload("userId");
                System.out.println(userId);
            } catch (Exception e) {
                System.out.println("token已过期");
            }
        }
    }

    @Test
    public void verifyToken() {
        String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2NDg3MTcyMTIsImV4cCI6MTY0ODcxNzIxNCwiaWF0IjoxNjQ4NzE3MjEyLCJ1c2VySWQiOjF9.ZWUkeBf9GZE5fSrItv22p6xgvdogW8CCG4dOH0a74Fw";
        JWTValidator jwtValidator = JWTValidator.of(testToken).validateDate();
    }


}
