package com.projetodevops.infrastructure.messaging.producer;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.domain.event.PedidoEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPedidoEventPublisher implements PedidoEventPublisher {

    private static final String TOPIC = "pedidos-criados";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPedidoEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(PedidoCriadoEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}