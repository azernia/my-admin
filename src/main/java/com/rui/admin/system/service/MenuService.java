package com.rui.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rui.admin.system.model.entity.Menu;

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
}