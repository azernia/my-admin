package com.rui.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rui.admin.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 系统权限表
 *
 * @author Rui
 * @since 2022-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permissions")
public class Permissions extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
    * 模块名
    */
    private String moduleName;


    /**
    * 功能名称
    */
    private String funcName;


    /**
    * 权限标识
    */
    private String authority;

}
