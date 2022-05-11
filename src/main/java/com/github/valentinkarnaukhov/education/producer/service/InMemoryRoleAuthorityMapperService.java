package com.github.valentinkarnaukhov.education.producer.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.valentinkarnaukhov.education.producer.service.InMemoryRoleAuthorityMapperService.Authority.*;

@Service
public class InMemoryRoleAuthorityMapperService implements RoleAuthorityMapper {

    public Collection<String> getAuthorities(String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.name().equals(role))
                .flatMap(r -> Stream.of(r.getAuthorities()))
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
    }

    @Getter
    public enum Role {
        ADMIN(COMPANY_CREATE, MESSAGE_SEND),
        USER(MESSAGE_SEND);

        Role(Authority... authorities) {
            this.authorities = authorities;
        }

        private final Authority[] authorities;
    }

    @Getter
    @AllArgsConstructor
    public enum Authority {
        COMPANY_CREATE("COMPANY_CREATE"),
        MESSAGE_SEND("MESSAGE_SEND");

        private final String authority;
    }

}
