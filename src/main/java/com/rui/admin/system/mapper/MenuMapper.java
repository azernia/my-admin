package com.rui.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rui.admin.system.model.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单表 Mapper 接口
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 获取菜单权限
     * @param userId 用户ID
     * @return 菜单权限列表
     */
    List<String> getMenuPermissions(@Param("userId") Integer userId);
}
