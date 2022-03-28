package com.rui.admin.system.mapper;

import com.rui.admin.system.model.entity.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色用户关联表 Mapper 接口
 *
 * @author Rui
 * @since 2022-03-23
 */
public interface RoleUserMapper extends BaseMapper<RoleUser> {
    /**
     * 通过用户ID获取角色ID集合
     *
     * @param userId 用户id
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> getRoleIdsByUserId(@Param("userId") Integer userId);
}
