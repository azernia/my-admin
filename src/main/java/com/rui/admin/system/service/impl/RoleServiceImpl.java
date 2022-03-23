package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.commons.entity.PageResult;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.commons.utils.PageUtils;
import com.rui.admin.system.model.entity.Role;
import com.rui.admin.system.mapper.RoleMapper;
import com.rui.admin.system.model.request.RoleDTO;
import com.rui.admin.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            queryWrapper.like(Role::getName, roleDTO.getName());
        }
        return new PageResult<>(page(PageUtils.defaultPage(), queryWrapper));
    }

    @Override
    public RespBean list(RoleDTO roleDTO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotNull(roleDTO.getName())) {
            queryWrapper.like(Role::getName, roleDTO.getName());
        }
        return RespBean.success(list(queryWrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespBean add(RoleDTO roleDTO) {
        Role role = BeanCopyUtils.copyBean(roleDTO, Role.class);
        if (save(role)) {
            return RespBean.success("添加成功");
        } else {
            throw new BusinessException("添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespBean edit(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RespBean delete(Integer id) {
        return null;
    }
}
