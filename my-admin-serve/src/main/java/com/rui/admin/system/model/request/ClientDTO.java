package com.rui.admin.system.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 系统客户端信息 DTO
 *
 * @author Rui
 * @since 2022-06-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 必填，Oauth2 client_id
     */
    private String clientId;

    /**
     * 必填，Oauth2 client_secret
     */
    private String clientSecret;

    /**
     * 必填，Oauth2 权限范围，比如 read，write，all 等可自定义
     */
    private String scope;

    /**
     * 可选，资源id集合，多个资源用英文逗号隔开
     */
    private String resourceIds;

    /**
     * 必填，Oauth2 授权类型，支持类型：authorization_code,password,refresh_token,implicit,client_credentials,多个用英文逗号隔开
     */
    private String authorizedGrantTypes;

    /**
     * 可选，客户端的重定向URI,当grant_type为authorization_code或implicit时,此字段是需要的,逗号分隔
     */
    private String registeredRedirectUris;

    /**
     * 可选，指定客户端所拥有的Spring Security的权限值
     */
    private String authorities;

    /**
     * 可选，access_token的有效时间值(单位:秒)，不填写框架(类refreshTokenValiditySeconds)默认12小时
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 可选，refresh_token的有效时间值(单位:秒)，不填写框架(类refreshTokenValiditySeconds)默认30天
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 预留字段，格式必须是json
     */
    private String additionalInformation;

    /**
     * 授权码模式自动审批scopes，以英文逗号分隔
     */
    private String autoApprove;
}
