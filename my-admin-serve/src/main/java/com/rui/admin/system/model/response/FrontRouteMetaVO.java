package com.rui.admin.system.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author rui
 * @since 2022/3/29 09:56
 */
@Data
@AllArgsConstructor
public class FrontRouteMetaVO implements Serializable {
    private String title;

    private String icon;

    private List<String> roles;
}
