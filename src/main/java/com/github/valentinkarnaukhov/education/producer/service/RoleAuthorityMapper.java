package com.github.valentinkarnaukhov.education.producer.service;

import java.util.Collection;

public interface RoleAuthorityMapper {

    Collection<String> getAuthorities(String role);

}
