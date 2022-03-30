package com.rui.admin.system.controller;


import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.service.PermissionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统权限表 前端控制器
 *
 * @author Rui
 * @since 2022-03-23
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/rui/admin/permissions")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;

    @ApiOperation("获取权限列表")
    @GetMapping("/getPermissions")
    public RespBean getPermissions() {
        return permissionsService.getPermissions();
    }

    @ApiOperation("同步权限")
    @PostMapping("/syncPermission")
    public RespBean syncPermission(String permissions) {
        return permissionsService.syncPermission(permissions);
    }
}
