package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.model.Company;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDto.CompanyPostResponse saveCompany(CompanyDto.CompanyPostRequest company) {
        Company savedCompany = saveCompany(company.getName());

        CompanyDto.CompanyPostResponse response = new CompanyDto.CompanyPostResponse();
        response.setUuid(savedCompany.getUuid());
        return response;
    }

    private Company saveCompany(String name) {
        Company company = new Company();
        company.setName(name);
        company.setUuid(UUID.randomUUID());
        return companyRepository.save(company);
    }

}
