package com.rui.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rui.admin.system.model.entity.RoleMenu;

import java.util.List;

/**
 * 角色菜单关联表 服务类
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 通过菜单ID获取角色名称
     *
     * @param menuId 菜单id
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNameByMenuId(Integer menuId);
}
