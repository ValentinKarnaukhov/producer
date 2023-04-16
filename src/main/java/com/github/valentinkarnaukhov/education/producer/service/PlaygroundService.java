package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.producer.model.Company;
import com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundService {

    private final CompanyRepository companyRepository;

    public List<Company> getCompanyInterface(){
        return companyRepository.findAll();
    }

    public List<Company> getCompanyRepository(){
        return companyRepository.findAllByIdNotNull();
    }
}
