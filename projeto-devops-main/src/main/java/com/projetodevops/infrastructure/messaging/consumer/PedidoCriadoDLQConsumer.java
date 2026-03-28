package com.projetodevops.infrastructure.messaging.consumer;

import org.springframework.stereotype.Component;
import com.projetodevops.service.EventDLQAuditService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Component
public class PedidoCriadoDLQConsumer {

    private final EventDLQAuditService auditService;

    public PedidoCriadoDLQConsumer(EventDLQAuditService auditService) {
        this.auditService = auditService;
    }

    @KafkaListener(
        topics = ".*\\.DLQ",
        groupId = "grupo-dlq-audit"
    )
    public void consumirDLQ(
        Object payload,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
        @Header(KafkaHeaders.OFFSET) long offset,
        @Header(name = "kafka_dlt-exception-stacktrace", required = false) String stacktrace
    ) {

        Exception exception = new RuntimeException(stacktrace != null ? stacktrace : "Stacktrace não disponível");

        auditService.registrarEventDLQ(
            topic,
            partition,
            offset,
            payload,
            exception
        );

    }
}
