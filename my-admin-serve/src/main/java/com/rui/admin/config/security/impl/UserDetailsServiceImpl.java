package com.rui.admin.config.security.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.service.MenuService;
import com.rui.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 菜单权限列表
        List<String> menuPermissions = menuService.getMenuPermissions(user.getId());
        List<String> collect = menuPermissions.stream().filter(Objects::nonNull).collect(Collectors.toList());
        // 获取所有菜单
        List<Menu> menus = menuService.list();
        return new LoginUser(user, collect, menus);
    }
}
