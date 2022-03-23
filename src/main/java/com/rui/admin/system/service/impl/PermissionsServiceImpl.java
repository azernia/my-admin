package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.Permissions;
import com.rui.admin.system.mapper.PermissionsMapper;
import com.rui.admin.system.service.PermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 系统权限表 服务实现类
*
* @author Rui
* @since 2022-03-23
*/
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}
