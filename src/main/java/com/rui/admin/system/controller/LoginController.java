package com.rui.admin.system.controller;

import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rui
 * @since 2022/3/22 16:00
 */
@RestController
@RequestMapping("/rui/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public RespBean login(User user) {
        return loginService.doLogin(user);
    }

}
