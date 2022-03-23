package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.mapper.UserMapper;
import com.rui.admin.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 系统用户 服务实现类
*
* @author Rui
* @since 2022-03-16
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
