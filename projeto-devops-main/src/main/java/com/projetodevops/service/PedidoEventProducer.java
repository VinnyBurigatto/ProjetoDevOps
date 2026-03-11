package com.projetodevops.service;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventProducer {

    private static final String TOPIC = "pedidos-criados";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PedidoEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(PedidoCriadoEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}