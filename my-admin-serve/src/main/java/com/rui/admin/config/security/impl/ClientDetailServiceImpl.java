package com.rui.admin.config.security.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rui.admin.commons.constants.SystemConstants;
import com.rui.admin.system.model.entity.Client;
import com.rui.admin.system.service.ClientService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * description: 客户端详情服务类
 * <hr/>
 * date: 2022/6/28 09:49
 *
 * @author rui
 */
@Service
public class ClientDetailServiceImpl implements ClientDetailsService {

    private final ClientService clientService;

    public ClientDetailServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientService.getOne(new LambdaQueryWrapper<Client>().eq(Client::getClientId, clientId));
        if (Objects.isNull(client)) {
            throw new ClientRegistrationException("client not found");
        }
        return buildClient(client);
    }

    /**
     * 构建客户端
     *
     * @param client 客户端
     * @return {@link ClientDetails}
     */
    private ClientDetails buildClient(Client client) {
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientId());
        baseClientDetails.setClientSecret(client.getClientSecret());
        if (StringUtils.isNotEmpty(client.getScope())) {
            baseClientDetails.setScope(Arrays.asList(client.getScope().split(SystemConstants.COMMA_SEPARATE)));
        }
        if (StringUtils.isNotEmpty(client.getResourceIds())) {
            baseClientDetails.setResourceIds(Arrays.asList(client.getResourceIds().split(SystemConstants.COMMA_SEPARATE)));
        }
        if (StringUtils.isNotEmpty(client.getAuthorizedGrantTypes())) {
            baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(SystemConstants.COMMA_SEPARATE)));
        }
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        return baseClientDetails;
    }
}
