package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.commons.constants.RedisConstant;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.commons.utils.RedisCacheUtils;
import com.rui.admin.commons.utils.SecondaryJwtUtils;
import com.rui.admin.commons.utils.UserUtils;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.model.request.UserDTO;
import com.rui.admin.system.model.response.FrontRouteMetaVO;
import com.rui.admin.system.model.response.UserVO;
import com.rui.admin.system.service.LoginService;
import com.rui.admin.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rui
 * @since 2022/3/22 16:08
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private MenuService menuService;

    @Override
    public RespBean doLogin(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (ObjectUtil.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String jwtToken = SecondaryJwtUtils.createJwtToken(user.getId());
        // 获取所有菜单
        List<Menu> menus = menuService.list(new LambdaQueryWrapper<Menu>().ne(Menu::getParentId, -1));
        // 存入 Redis
        redisCacheUtils.setCacheObject(RedisConstant.LOGIN + user.getId(), loginUser);
        // 清空 Redis 菜单缓存
        redisCacheUtils.deleteObject(RedisConstant.LOGIN_MENU);
        redisCacheUtils.setCacheList(RedisConstant.LOGIN_MENU, menus);
        // 返回 Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", jwtToken);
        return RespBean.success( "登录成功", resultMap);
    }

    @Override
    public RespBean logout() {
        // 获取 SecurityContextHolder 中的用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        // 删除 redis 中的值
        redisCacheUtils.deleteObject(RedisConstant.LOGIN + userId);
        // 删除 Redis 菜单数据
        redisCacheUtils.deleteObject(RedisConstant.LOGIN_MENU);
        return RespBean.success();
    }

    @Override
    public RespBean sidebarMenus() {
        List<Menu> menus = redisCacheUtils.getCacheList(RedisConstant.LOGIN_MENU);
        // 获取当前用户拥有的菜单
        List<Integer> menuIds = menuService.getMenuIds(UserUtils.getCurrentUserId());
        Iterator<Menu> iterator = menus.iterator();
        // 移除没有关联角色的目录
        // TODO 后续优化
        while (iterator.hasNext()) {
            boolean hasRole = false;
            Menu next = iterator.next();
            for (Integer menuId : menuIds) {
                if (next.getId().equals(menuId)) {
                    hasRole = true;
                    break;
                }
            }
            if (!hasRole) {
                iterator.remove();
            }
        }
        return RespBean.success(handleMenus(menus, 1));
    }

    @Override
    public RespBean getUserInfo() {
        LoginUser loginUser = redisCacheUtils.getCacheObject(RedisConstant.LOGIN + UserUtils.getCurrentUserId());
        User user = loginUser.getUser();
        UserVO userVO = BeanCopyUtils.copyBean(user, UserVO.class);
        List<String> menuPermissions = menuService.getMenuPermissions(user.getId()).stream().filter(Objects::nonNull).filter(ObjectUtil::isNotEmpty).collect(Collectors.toList());
        userVO.setRoles(menuPermissions);
        return RespBean.success(userVO);
    }

    /**
     * 处理菜单
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    private List<Map<String, Object>> handleMenus(List<Menu> menus, Integer parentId) {
        // 生成菜单树形
        List<Map<String, Object>> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId().equals(parentId)) {
                Map<String, Object> map = new HashMap<>();
                map.put("path", menu.getPath());
                map.put("component", menu.getComponent());
                map.put("name", menu.getComponentName());
                map.put("alwaysShow", menu.getAlwaysShow());
                List<String> menuRoles = new ArrayList<>();
                menuRoles.add(menuService.getMenuPermissionByMenuId(menu.getId()));
                map.put("meta", new FrontRouteMetaVO(menu.getName(), menu.getIcon(), menuRoles.get(0) == null || ObjectUtil.isEmpty(menuRoles.get(0)) ? null : menuRoles));
                map.put("children", handleMenus(menus, menu.getId()));
                list.add(map);
            }
        }
        return list;
    }
}
