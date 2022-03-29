package com.rui.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rui.admin.system.model.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单表 服务类
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获取菜单权限
     * @param userId 用户ID
     * @return 菜单权限列表
     */
    List<String> getMenuPermissions(Integer userId);

    /**
     * 获取菜单ID集合
     *
     * @param userId 用户id
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> getMenuIds(Integer userId);

    /**
     * 通过菜单ID获取菜单权限
     *
     * @param menuId 菜单id
     * @return {@link String}
     */
    String getMenuPermissionByMenuId(Integer menuId);
}
