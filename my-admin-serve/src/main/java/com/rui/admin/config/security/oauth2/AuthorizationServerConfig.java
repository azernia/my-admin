package com.rui.admin.config.security.oauth2;

import com.rui.admin.commons.constants.OAuth2Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * description: OAuth2 认证服务器配置
 * <hr/>
 * date: 2022/6/27 14:47
 *
 * @author rui
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(TokenStore tokenStore, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(OAuth2Constants.CLIENT_ID)
                .secret(OAuth2Constants.CLIENT_SECRET)
                .scopes(OAuth2Constants.SCOPE_ALL)
                .authorizedGrantTypes(OAuth2Constants.GRANT_TYPE_PASSWORD);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService);
    }
}
