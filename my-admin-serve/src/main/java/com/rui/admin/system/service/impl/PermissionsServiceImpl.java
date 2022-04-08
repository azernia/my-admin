package com.rui.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.commons.utils.JsonUtils;
import com.rui.admin.system.mapper.PermissionsMapper;
import com.rui.admin.system.model.entity.Permissions;
import com.rui.admin.system.service.PermissionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
* 系统权限表 服务实现类
*
* @author Rui
* @since 2022-03-23
*/
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

    @Override
    public RespBean getPermissions() {
        return RespBean.success(list());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespBean syncPermission(String permissions) {
        List<Permissions> permissionsList = JsonUtils.json2Array(permissions, Permissions.class);
        permissionsList.forEach(permission -> {
            permission.setId(UUID.randomUUID().toString());
        });
        remove(null);
        if (saveBatch(permissionsList)) {
            return RespBean.success("同步成功");
        } else {
            throw new BusinessException("同步失败");
        }
    }
}
