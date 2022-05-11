package com.github.valentinkarnaukhov.education.producer.config;

import com.github.valentinkarnaukhov.education.producer.service.InMemoryRoleAuthorityMapperService;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    private final InMemoryRoleAuthorityMapperService roleAuthorityMapperService;

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public GrantedAuthoritiesMapper authorityMapper() {
        return authorities -> authorities.stream()
                .flatMap(authority -> roleAuthorityMapperService.getAuthorities(authority.getAuthority()).stream())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

}
