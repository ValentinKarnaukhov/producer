package com.github.valentinkarnaukhov.education.producer.controller;


import com.github.valentinkarnaukhov.education.producer.model.Company;
import com.github.valentinkarnaukhov.education.producer.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @GetMapping("/interface")
    public List<Company> interfaceGet() {
        return playgroundService.getCompanyInterface();
    }

    @GetMapping("/repository")
    public List<Company> repositoryGet() {
        return playgroundService.getCompanyRepository();
    }

}
