package com.rui.admin.system.controller;


import com.rui.admin.commons.entity.PageResult;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.Role;
import com.rui.admin.system.model.request.RoleDTO;
import com.rui.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 系统角色 前端控制器
 *
 * @author Rui
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/rui/admin/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('get:/rui/admin/role/pageList')")
    @GetMapping("/pageList")
    public PageResult<Role> pageList(RoleDTO roleDTO) {
        return roleService.pageList(roleDTO);
    }

    @GetMapping("/list")
    public RespBean list(RoleDTO roleDTO) {
        return roleService.list(roleDTO);
    }

    @PostMapping("/add")
    public RespBean add(RoleDTO roleDTO) {
        return roleService.add(roleDTO);
    }

    @PutMapping("/edit")
    public RespBean edit(RoleDTO roleDTO) {
        return roleService.edit(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public RespBean delete(@PathVariable("id") Integer id) {
        return roleService.delete(id);
    }
}
