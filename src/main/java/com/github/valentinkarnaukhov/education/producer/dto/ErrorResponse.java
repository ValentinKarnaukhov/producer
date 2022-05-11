package com.github.valentinkarnaukhov.education.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private String error;
    private Integer status;

}
