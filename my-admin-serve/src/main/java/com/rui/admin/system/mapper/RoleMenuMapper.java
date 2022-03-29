package com.rui.admin.system.mapper;

import com.rui.admin.system.model.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单关联表 Mapper 接口
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 通过菜单ID获取角色名称
     *
     * @param menuId 菜单id
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNameByMenuId(@Param("menuId") Integer menuId);
}
