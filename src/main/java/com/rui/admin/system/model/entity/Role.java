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
 * 系统角色
 *
 * @author Rui
 * @since 2022-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.AUTO)
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
