package com.rui.admin.system.model.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * 系统角色 DTO
 *
 * @author Rui
 * @since 2022-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色中文名
     */
    private String nameZh;

    /**
     * 是否被禁用
     */
    private Boolean enable;
}
