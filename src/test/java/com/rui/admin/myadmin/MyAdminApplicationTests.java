package com.rui.admin.myadmin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
class MyAdminApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setAddress("湖南省长沙市岳麓区");
        user.setBirthday(LocalDateTime.of(1987, 5, 5, 16, 22));
        user.setGender(0);
        user.setEmail("xxx@qq.com");
        user.setPhone("17685178016");
        userService.save(user);
    }

    @Test
    public void checkLogin() {
        User admin = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, "admin"));
        System.out.println(passwordEncoder.matches("123456", admin.getPassword()));
    }

}
