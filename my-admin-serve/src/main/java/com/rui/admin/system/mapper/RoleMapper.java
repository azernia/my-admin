package com.rui.admin.system.mapper;

import com.rui.admin.system.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色 Mapper 接口
 *
 * @author Rui
 * @since 2022-03-22
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户ID获取角色名称
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNameByUserId(@Param("userId") Integer userId);
}
