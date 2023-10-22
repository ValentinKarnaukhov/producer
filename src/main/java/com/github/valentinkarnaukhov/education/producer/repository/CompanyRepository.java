package com.github.valentinkarnaukhov.education.producer.repository;

import com.github.valentinkarnaukhov.education.producer.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByUuid(UUID uuid);

    boolean existsByName(String name);

}
