package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.kafkastarter.event.MessageEvent;
import com.github.valentinkarnaukhov.education.kafkastarter.service.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageEventPublisher extends KafkaEventPublisher<MessageEvent> {

        private final String TOPIC_NAME = "messages";
    private final KafkaTemplate<UUID, MessageEvent> kafkaTemplate;

    @Override
    protected KafkaTemplate<UUID, MessageEvent> getKafkaTemplate() {
        return kafkaTemplate;
    }

    @Override
    public String getTopicName() {
        return TOPIC_NAME;
    }
}
