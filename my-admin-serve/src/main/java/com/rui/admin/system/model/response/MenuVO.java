package com.rui.admin.system.model.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统菜单表 VO
 *
 * @author Rui
 * @since 2022-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 是否为根目录
     */
    private Boolean alwaysShow;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 父菜单名称
     */
    private String parentName;
}
