package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.RoleUser;
import com.rui.admin.system.mapper.RoleUserMapper;
import com.rui.admin.system.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 角色用户关联表 服务实现类
*
* @author Rui
* @since 2022-03-23
*/
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

    @Override
    public List<Integer> getRoleIdsByUserId(Integer userId) {
        return null;
    }
}
