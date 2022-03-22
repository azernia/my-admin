package com.rui.admin.system.service.impl;

import com.rui.admin.commons.entity.PageResult;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.system.model.entity.Role;
import com.rui.admin.system.mapper.RoleMapper;
import com.rui.admin.system.model.request.RoleDTO;
import com.rui.admin.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 系统角色 服务实现类
*
* @author Rui
* @since 2022-03-22
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public PageResult<Role> pageList(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RespBean add(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RespBean list(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RespBean edit(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RespBean delete(Integer id) {
        return null;
    }
}
