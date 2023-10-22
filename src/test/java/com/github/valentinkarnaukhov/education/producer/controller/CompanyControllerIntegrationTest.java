package com.github.valentinkarnaukhov.education.producer.controller;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
    public void saveCompanyShouldReturnsGeneratedUuid() {
        CompanyDto.CompanyPostRequest company = new CompanyDto.CompanyPostRequest();
        company.setName("CompanyWithUUID");

        UUID companyUuid = companyController.postCompany(company).getUuid();

        Assertions.assertTrue(companyRepository.existsByUuid(companyUuid));
    }

    @Test
    public void saveCompanyShouldRejectAlreadyExistingName() {
        CompanyDto.CompanyPostRequest company = new CompanyDto.CompanyPostRequest();
        company.setName("CompanyName");

        companyController.postCompany(company);
        Executable saveCompanyWithSameNameExecutable = () -> companyController.postCompany(company);

        Assertions.assertThrows(IllegalArgumentException.class, saveCompanyWithSameNameExecutable);
    }

}
