package com.github.valentinkarnaukhov.education.producer.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import static com.github.valentinkarnaukhov.education.producer.service.InMemoryRoleAuthorityMapperService.Authority.COMPANY_CREATE;
import static com.github.valentinkarnaukhov.education.producer.service.InMemoryRoleAuthorityMapperService.Authority.MESSAGE_SEND;

@RequiredArgsConstructor
@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private final GrantedAuthoritiesMapper authoritiesMapper;

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(authoritiesMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(keycloakAuthenticationProcessingFilter(), LogoutFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/company").hasAnyAuthority(COMPANY_CREATE.getAuthority())
                .antMatchers(HttpMethod.POST, "/api/v1/message").hasAnyAuthority(MESSAGE_SEND.getAuthority())
                .anyRequest().permitAll();
    }

}
