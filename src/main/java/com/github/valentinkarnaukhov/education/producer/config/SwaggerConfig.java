package com.github.valentinkarnaukhov.education.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${token.url}")
    private String tokenUrl;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private OAuth securitySchema() {
        GrantType clientCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
        return new OAuth("oauth", Collections.emptyList(), Collections.singletonList(clientCredentialsGrant));
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return List.of(new SecurityReference("oauth", new AuthorizationScope[0]));
    }


}
