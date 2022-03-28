package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.mapper.MenuMapper;
import com.rui.admin.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 系统菜单表 服务实现类
*
* @author Rui
* @since 2022-03-23
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> getMenuPermissions(Integer userId) {
        return baseMapper.getMenuPermissions(userId);
    }

    @Override
    public List<Integer> getMenuIds(Integer userId) {
        return baseMapper.getMenuIds(userId);
    }
}
