package com.projetodevops.infrastructure.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EventSerializer {

    private final ObjectMapper objectMapper;

    public EventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String serializar(Object evento) {
        try {
            return objectMapper.writeValueAsString(evento);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar evento para JSON", e);
        }
    }

    public <T> T desserializar(String payload, Class<T> clazz) {
        try {
            return objectMapper.readValue(payload, clazz);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desserializar evento", e);
        }
    }

}
