package com.rui.admin.system.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 系统权限表 DTO
 *
 * @author Rui
 * @since 2022-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsDTO implements Serializable {

    /**
     * ID
     */
    private Integer id;

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
