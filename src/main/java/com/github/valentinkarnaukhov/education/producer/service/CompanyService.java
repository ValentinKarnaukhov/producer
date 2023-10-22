package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.model.Company;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    private Company saveCompany(String companyName) {
        this.validateCompanyName(companyName);

        Company company = new Company();
        company.setName(companyName);
        company.setUuid(UUID.randomUUID());
        return companyRepository.save(company);
    }

    private void validateCompanyName(String companyName) {
        if (companyRepository.existsByName(companyName)) {
            throw new IllegalArgumentException(String.format("Company with such name already exists: %s", companyName));
        }
    }

    public void validateCompanyExistence(UUID companyUuid) throws EntityNotFoundException {
        if (!companyRepository.existsByUuid(companyUuid)) {
            throw new EntityNotFoundException(String.format("Company with such UUID doesn't exist: %s", companyUuid));
        }
    }

}
