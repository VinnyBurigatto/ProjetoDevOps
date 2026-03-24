package com.projetodevops.infrastructure.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;

@Component

public class EventSerializer {

    private final ObjectMapper objectMapper;

    public EventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String serializar(Object evento) {
        try { return objectMapper.writeValueAsString(evento);}
        catch (Exception e) { throw new RuntimeException("Erro ao serializar evento para JSON", e);}
    }

}
