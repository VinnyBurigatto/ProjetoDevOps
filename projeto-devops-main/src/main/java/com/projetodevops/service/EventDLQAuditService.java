package com.projetodevops.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetodevops.repository.document.EventDLQAuditDocument;
import com.projetodevops.repository.mongo.EventDLQAuditMongoRepository;
import org.springframework.stereotype.Service;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Service
public class EventDLQAuditService {

    private final EventDLQAuditMongoRepository repository;
    private final ObjectMapper objectMapper;

    public EventDLQAuditService(EventDLQAuditMongoRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public void registrarEventDLQ(String topic, Integer partition, Long offset, Object payload, Exception exception) {

        String payloadJson = converterPayload(payload);

        String stacktrace = converterStacktrace(exception);

        EventDLQAuditDocument audit = new EventDLQAuditDocument(topic, partition, offset, payloadJson, stacktrace, LocalDateTime.now());

        repository.save(audit);

    }

    private String converterPayload(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return "Erro ao serializar payload";
        }
    }

    private String converterStacktrace(Exception exception) {

        StringWriter sw = new StringWriter();

        exception.printStackTrace(new PrintWriter(sw));

        return sw.toString();

    }

}
