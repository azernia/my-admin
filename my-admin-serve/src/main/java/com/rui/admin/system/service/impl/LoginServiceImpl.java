package com.rui.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.utils.BeanCopyUtils;
import com.rui.admin.commons.utils.RedisCacheUtils;
import com.rui.admin.commons.utils.SecondaryJwtUtils;
import com.rui.admin.commons.utils.UserUtils;
import com.rui.admin.config.security.entity.LoginUser;
import com.rui.admin.system.model.entity.Menu;
import com.rui.admin.system.model.entity.User;
import com.rui.admin.system.model.request.UserDTO;
import com.rui.admin.system.model.response.UserVO;
import com.rui.admin.system.service.LoginService;
import com.rui.admin.system.service.MenuService;
import com.rui.admin.system.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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
        UserVO userVO = BeanCopyUtils.copyBean(user, UserVO.class);
        String jwtToken = SecondaryJwtUtils.createJwtToken(user.getId());
        // 存入 Redis
        redisCacheUtils.setCacheObject("login:"+user.getId(), loginUser);
        // 返回 Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", jwtToken);
        resultMap.put("userInfo", userVO);
        return RespBean.success("登录成功", resultMap);
    }

    @Override
    public RespBean logout() {
        // 获取 SecurityContextHolder 中的用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        // 删除 redis 中的值
        redisCacheUtils.deleteObject("login:"+userId);
        return RespBean.success();
    }

    @Override
    public RespBean sidebarMenus() {
        Integer userId = UserUtils.getCurrentUserId();
        LoginUser loginUser = redisCacheUtils.getCacheObject("login:" + userId);
        List<Menu> menus = loginUser.getMenus();
        // 获取当前用户拥有的菜单
        List<Integer> menuIds = menuService.getMenuIds(userId);
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
        return RespBean.success(handleMenus(menus, -1));
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
                map.put("name", menu.getName());
                map.put("component", menu.getComponent());
                map.put("componentName", menu.getComponentName());
                map.put("icon", menu.getIcon());
                map.put("children", handleMenus(menus, menu.getId()));
                list.add(map);
            }
        }
        return list;
    }
}
