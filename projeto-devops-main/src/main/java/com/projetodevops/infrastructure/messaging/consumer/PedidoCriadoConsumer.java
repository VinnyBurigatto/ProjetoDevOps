package com.projetodevops.infrastructure.messaging.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.infrastructure.messaging.idempotency.IdempotencyService;

@Component
public class PedidoCriadoConsumer {

    private final IdempotencyService idempotencyService;

    public PedidoCriadoConsumer(IdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @KafkaListener(topics = "pedidos-criados", groupId = "grupo-pedidos")
    
    public void consumir(PedidoCriadoEvent event) {

        if (event.getCliente().equals("ERRO_DLQ")) {
            throw new RuntimeException("Simulação de erro para teste DLQ");
        }

        if (idempotencyService.alreadyProcessed(event.getEventId())) {
            System.out.println("Evento já processado: " + event.getEventId());
                return;
        }

        System.out.println("Processando pedido: " + event.getPedidoId());

        idempotencyService.markProcessed(event.getEventId(), "PedidoCriadoConsumer");

    }
}
