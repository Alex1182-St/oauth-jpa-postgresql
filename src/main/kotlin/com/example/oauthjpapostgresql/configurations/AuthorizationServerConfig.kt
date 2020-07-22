package com.example.oauthjpapostgresql.configurations

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.TokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(

        private val passwordEncoder : PasswordEncoder,

        private val tokenStore : TokenStore,

        private val accessTokenConverter : AccessTokenConverter,

        private val authenticationManager : AuthenticationManager

) : AuthorizationServerConfigurerAdapter() {

    override fun configure(security : AuthorizationServerSecurityConfigurer) {
        security.allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients : ClientDetailsServiceConfigurer) {
        clients
                .inMemory()
                .withClient("vue-client").secret(passwordEncoder.encode("vue-password"))
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .scopes("any")
                .accessTokenValiditySeconds(60 * 60 * 2)  // 2h
                .refreshTokenValiditySeconds(60 * 60 * 8) // 8h
    }

    override fun configure(endpoints : AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager)
    }
}