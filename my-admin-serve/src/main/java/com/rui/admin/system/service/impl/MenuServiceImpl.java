package com.rui.admin.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rui.admin.commons.constants.RedisConstant;
import com.rui.admin.commons.constants.RespConstant;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.commons.utils.RedisCacheUtils;
import com.rui.admin.system.mapper.MenuMapper;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.model.request.MenuDTO;
import com.rui.admin.system.model.response.MenuVO;
import com.rui.admin.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 系统菜单表 服务实现类
*
* @author Rui
* @since 2022-03-23
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

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
        List<MenuVO> menus = baseMapper.menuList();
        return RespBean.success(handleMenuList(menus, -1));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespBean add(MenuDTO menuDTO) {
        Menu menu = BeanCopyUtils.copyBean(menuDTO, Menu.class);
        if (save(menu)) {
            redisCacheUtils.deleteObject(RedisConstant.LOGIN_MENU);
            redisCacheUtils.setCacheList(RedisConstant.LOGIN_MENU, list());
            return RespBean.success(RespConstant.ADD_SUCCESS);
        } else {
            throw new BusinessException(RespConstant.ADD_FAIL);
        }
    }

    @Override
    public RespBean edit(MenuDTO menuDTO) {
        Menu menu = queryMenu(menuDTO.getId());
        CopyOptions copyOptions = CopyOptions.create().setEditable(Menu.class).ignoreNullValue();
        BeanUtil.copyProperties(menuDTO, menu, copyOptions);
        if (updateById(menu)) {
            return RespBean.success(RespConstant.UPDATE_SUCCESS);
        } else {
            throw new BusinessException(RespConstant.UPDATE_FAIL);
        }
    }

    /**
     * 处理菜单列表
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    private List<Map<String, Object>> handleMenuList(List<MenuVO> menus, Integer parentId) {
        // 生成菜单树形
        List<Map<String, Object>> list = new ArrayList<>();
        for (MenuVO menu : menus) {
            if (menu.getParentId().equals(parentId)) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", menu.getId());
                map.put("name", menu.getName());
                map.put("component", menu.getComponent());
                map.put("componentName", menu.getComponentName());
                map.put("icon", menu.getIcon());
                map.put("parentId", menu.getParentId());
                map.put("parentName", menu.getParentName());
                map.put("path", menu.getPath());
                map.put("alwaysShow", menu.getAlwaysShow());
                map.put("authority", menu.getAuthority());
                map.put("hidden", menu.getHidden());
                map.put("sort", menu.getSort());
                map.put("children", handleMenuList(menus, menu.getId()));
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 查询菜单
     *
     * @param id id
     * @return {@link Menu}
     */
    private Menu queryMenu(Integer id) {
        Menu menu = getById(id);
        if (ObjectUtil.isNull(menu)) {
            throw new BusinessException(RespConstant.NO_EXIST);
        }
        return menu;
    }
}
