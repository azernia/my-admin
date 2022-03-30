package com.rui.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.Permissions;

/**
 * 系统权限表 服务类
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface PermissionsService extends IService<Permissions> {

    /**
     * 获得权限
     *
     * @return {@link RespBean}
     */
    RespBean getPermissions();

    /**
     * 同步许可
     *
     * @param permissions 权限
     * @return {@link RespBean}
     */
    RespBean syncPermission(String permissions);
}
