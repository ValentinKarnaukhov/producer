package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.kafkastarter.event.MessageEvent;
import com.github.valentinkarnaukhov.education.producer.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final EventPublisherService eventPublisherService;

    public void publishMessage(MessageDto.MessagePostRequest message) {
        publishMessage(message.getCompanyUuid(), message.getMessage());
    }

    private void publishMessage(UUID companyUuid, String message) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCompanyUuid(companyUuid);
        messageEvent.setMessage(message);
        messageEvent.setTimestamp(OffsetDateTime.now());

        eventPublisherService.send(companyUuid, null, messageEvent);
    }

}
