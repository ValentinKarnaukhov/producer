package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.kafkastarter.event.MessageEvent;
import com.github.valentinkarnaukhov.education.producer.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageEventPublisher messageEventPublisher;

    public void publishMessage(MessageDto.MessagePostRequest message) {
        publishMessage(message.getCompanyUuid(), message.getText());
    }

    private void publishMessage(UUID companyUuid, String text) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCompanyUuid(companyUuid);
        messageEvent.setText(text);
        messageEvent.setTimestamp(Instant.now());

        messageEventPublisher.send(companyUuid, null, messageEvent);
    }

}
