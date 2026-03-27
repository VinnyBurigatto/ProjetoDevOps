package com.projetodevops.infrastructure.messaging.producer;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.domain.event.PedidoEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import com.projetodevops.infrastructure.tracing.CorrelationIdContext;

@Component
public class KafkaPedidoEventPublisher implements PedidoEventPublisher {

    private static final String TOPIC = "pedidos-criados";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPedidoEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(PedidoCriadoEvent event) {
        
        String correlationId = CorrelationIdContext.getCorrelationId();

        Message<PedidoCriadoEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, TOPIC).setHeader("X-Correlation-Id", correlationId).build();

        kafkaTemplate.send(message);
    }
}