package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.commons.entity.PageResult;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.PageUtils;
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
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotNull(roleDTO.getName())) {
            queryWrapper.eq(Role::getName, roleDTO.getName());
        }
        return new PageResult<>(page(PageUtils.defaultPage(), queryWrapper));
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
