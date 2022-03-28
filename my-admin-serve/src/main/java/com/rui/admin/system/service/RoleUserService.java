package com.rui.admin.system.service;

import com.rui.admin.system.model.entity.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色用户关联表 服务类
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface RoleUserService extends IService<RoleUser> {
    /**
     * 通过用户ID获取角色ID集合
     *
     * @param userId 用户id
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> getRoleIdsByUserId(Integer userId);
}
