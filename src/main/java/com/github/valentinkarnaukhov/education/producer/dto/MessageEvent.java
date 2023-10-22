package com.github.valentinkarnaukhov.education.producer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class MessageEvent {

    private UUID companyUuid;
    private UUID userUuid;
    private String text;
    private Instant timestamp;

}
