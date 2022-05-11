package com.github.valentinkarnaukhov.education.producer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class CompanyDto {

    @Getter
    @Setter
    public static class CompanyPostRequest {
        private String name;
    }

    @Setter
    @Getter
    public static class CompanyPostResponse {
        private UUID uuid;
    }

}
