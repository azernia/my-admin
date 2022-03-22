package com.rui.admin.config.security.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // TODO 权限列表
        return new LoginUser(user);
    }
}
