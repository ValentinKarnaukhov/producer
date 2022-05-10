package com.github.valentinkarnaukhov.education.producer.controller;

import com.github.valentinkarnaukhov.education.producer.dto.CompanyDto;
import com.github.valentinkarnaukhov.education.producer.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto.CompanyPostResponse postCompany(@RequestBody CompanyDto.CompanyPostRequest company) {
        return companyService.saveCompany(company);
    }

}
