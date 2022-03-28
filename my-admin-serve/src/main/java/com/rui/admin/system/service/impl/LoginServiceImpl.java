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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LoginUser loginUser = redisCacheUtils.getCacheObject("login:" + UserUtils.getCurrentUserId());
        List<Menu> menus = loginUser.getMenus();
        return null;
    }

    /**
     * 处理菜单
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    private List<Map<String, Object>> handleMenus(List<Menu> menus, Integer parentId) {
        List<Integer> menuIds = menuService.getMenuIds(UserUtils.getCurrentUserId());
        return null;
    }
}
