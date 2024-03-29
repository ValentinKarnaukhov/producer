package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.model.Company;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this.getClass());
    }

    @Test
    public void saveCompanyGenerateRandomUuid() {
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenAnswer(a -> a.getArguments()[0]);

        CompanyDto.CompanyPostRequest request = new CompanyDto.CompanyPostRequest();
        request.setName("CompanyName");
        UUID actualUuid = companyService.saveCompany(request).getUuid();

        Assertions.assertNotNull(actualUuid);
    }

}
