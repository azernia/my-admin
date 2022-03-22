package com.rui.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rui.admin.commons.entity.PageResult;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.Role;
import com.rui.admin.system.model.request.RoleDTO;

/**
 * 系统角色 服务类
 *
 * @author Rui
 * @since 2022-03-22
 */
public interface RoleService extends IService<Role> {

    /**
     * 页面列表
     *
     * @param roleDTO 角色dto
     * @return {@link PageResult}<{@link Role}>
     */
    PageResult<Role> pageList(RoleDTO roleDTO);

    /**
     * 添加角色
     * @param roleDTO 角色DTO
     * @return 操作结果
     */
    RespBean add(RoleDTO roleDTO);

    /**
     * 列表
     *
     * @param roleDTO 角色dto
     * @return {@link RespBean}
     */
    RespBean list(RoleDTO roleDTO);

    /**
     * 编辑
     *
     * @param roleDTO 角色dto
     * @return {@link RespBean}
     */
    RespBean edit(RoleDTO roleDTO);

    /**
     * 删除
     *
     * @param id id
     * @return {@link RespBean}
     */
    RespBean delete(Integer id);
}
