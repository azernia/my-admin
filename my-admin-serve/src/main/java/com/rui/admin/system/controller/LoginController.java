package com.rui.admin.system.controller;

import com.rui.admin.commons.annotation.log.SystemLog;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.request.UserDTO;
import com.rui.admin.system.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rui
 * @since 2022/3/22 16:00
 */
@Api(value = "登录管理", tags = "登录管理")
@RestController
@RequestMapping("/rui/admin")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public RespBean login(UserDTO userDTO) {
        return loginService.doLogin(userDTO);
    }

    @GetMapping("/logout")
    public RespBean logout() {
        return loginService.logout();
    }

    @GetMapping("/getUserInfo")
    @SystemLog(businessName = "获取用户信息")
    public RespBean getUserInfo() {
        return loginService.getUserInfo();
    }

    @GetMapping("/sidebarMenus")
    public RespBean sidebarMenus() {
        return loginService.sidebarMenus();
    }


}
