package com.rui.admin.system.model.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * 系统角色 VO
 *
 * @author Rui
 * @since 2022-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {

    /**
     * ID
     */
    private Integer id;





    /**
     * 角色名称
     */
    private String name;

    /**
     * 是否被禁用
     */
    private Boolean enable;
}
