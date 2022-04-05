package com.rui.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rui.admin.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 系统菜单表
 *
 * @author Rui
 * @since 2022-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 排序
     */
    private Integer sort;

}
