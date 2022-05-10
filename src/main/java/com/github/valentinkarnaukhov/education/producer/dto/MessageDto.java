package com.github.valentinkarnaukhov.education.producer.dto;

import lombok.Getter;

import java.util.UUID;

public class MessageDto {

    @Getter
    public static class MessagePostRequest{
        private UUID companyUuid;
        private String text;
    }

}
