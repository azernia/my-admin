package com.rui.admin.system.service.impl;

import com.rui.admin.commons.constants.RespConstant;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.mapper.MenuMapper;
import com.rui.admin.system.model.request.MenuDTO;
import com.rui.admin.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public String getMenuPermissionByMenuId(Integer menuId) {
        return baseMapper.getMenuPermissionByMenuId(menuId);
    }

    @Override
    public RespBean menuList() {
        List<Menu> menus = list();
        return RespBean.success(menus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespBean add(MenuDTO menuDTO) {
        Menu menu = BeanCopyUtils.copyBean(menuDTO, Menu.class);
        if (save(menu)) {
            return RespBean.success(RespConstant.ADD_SUCCESS);
        } else {
            throw new BusinessException(RespConstant.ADD_FAIL);
        }
    }
}
