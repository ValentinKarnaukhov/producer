package com.github.valentinkarnaukhov.education.producer.repository;

import com.github.valentinkarnaukhov.education.producer.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
