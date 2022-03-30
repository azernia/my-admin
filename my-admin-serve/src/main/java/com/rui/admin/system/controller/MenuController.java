package com.rui.admin.system.controller;


import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.request.MenuDTO;
import com.rui.admin.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统菜单表 前端控制器
 *
 * @author Rui
 * @since 2022-03-23
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/rui/admin/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("菜单列表")
    @Autowired
    @GetMapping("/menuList")
    public RespBean menuList() {
        return menuService.menuList();
    }


    @ApiOperation("添加菜单")
    @PostMapping("/add")
    public RespBean add(MenuDTO menuDTO) {
        return menuService.add(menuDTO);
    }
}
