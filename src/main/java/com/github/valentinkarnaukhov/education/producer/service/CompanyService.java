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

    private Company saveCompany(String name) {
        Company company = new Company();
        company.setName(name);
        company.setUuid(UUID.randomUUID());
        companyRepository.existsByUuid(company.getUuid());
        return companyRepository.save(company);
    }

    public void validateCompanyExistence(UUID uuid) throws EntityNotFoundException {
        if (!companyRepository.existsByUuid(uuid)) {
            throw new EntityNotFoundException(String.format("Company %s doesn't exist.", uuid));
        }
    }

}
