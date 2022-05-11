package com.github.valentinkarnaukhov.education.producer.service;

import com.github.valentinkarnaukhov.education.kafkastarter.event.MessageEvent;
import com.github.valentinkarnaukhov.education.producer.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageEventPublisher messageEventPublisher;
    private final CompanyService companyService;

    public void publishMessage(MessageDto.MessagePostRequest message) {
        publishMessage(message.getCompanyUuid(), message.getText());
    }

    private void publishMessage(UUID companyUuid, String text) {
        companyService.validateCompanyExistence(companyUuid);

        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCompanyUuid(companyUuid);
        messageEvent.setUserUuid(UUID.fromString(authentication.getName()));
        messageEvent.setText(text);
        messageEvent.setTimestamp(Instant.now());

        messageEventPublisher.send(companyUuid, null, messageEvent);
    }

}
