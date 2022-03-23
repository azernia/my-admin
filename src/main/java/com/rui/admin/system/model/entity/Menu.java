package com.rui.admin.system.model.entity;

import com.rui.admin.commons.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
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
    * 菜单组件
    */
    private String component;


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

}
