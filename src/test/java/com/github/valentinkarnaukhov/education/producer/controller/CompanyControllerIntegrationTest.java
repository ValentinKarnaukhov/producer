package com.github.valentinkarnaukhov.education.producer.controller;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles("integration-test")
@SpringBootTest
public class CompanyControllerIntegrationTest {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void saveCompanyReturnsGeneratedUuid() {
        CompanyDto.CompanyPostRequest request = new CompanyDto.CompanyPostRequest();
        request.setName("TestCompanyName");

        UUID companyUuid = companyController.postCompany(request).getUuid();

        Assertions.assertTrue(companyRepository.existsByUuid(companyUuid));
    }

}
