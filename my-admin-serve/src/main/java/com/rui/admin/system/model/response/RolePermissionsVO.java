package com.rui.admin.system.model.response;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * 系统角色权限表 VO
 *
 * @author Rui
 * @since 2022-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionsVO implements Serializable {

    /**
     * ID
     */
    private Integer id;





    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限标识
     */
    private String authority;
}
