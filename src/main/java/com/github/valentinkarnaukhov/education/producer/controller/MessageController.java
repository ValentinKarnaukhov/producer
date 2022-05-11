package com.github.valentinkarnaukhov.education.producer.controller;

import com.github.valentinkarnaukhov.education.producer.dto.MessageDto;
import com.github.valentinkarnaukhov.education.producer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(path = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postMessage(@RequestBody MessageDto.MessagePostRequest message) {
        messageService.publishMessage(message);
    }

}
