package com.projetodevops.infrastructure.messaging.consumer;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;

@Component
public class PedidoCriadoConsumer {

    @KafkaListener(topics = "pedidos-criados", groupId = "grupo-pedidos")
    public void consumir(PedidoCriadoEvent event, @Header(name = "X-Correlation-Id", required = false) String correlationId) {
        System.out.println("Pedido criado recebido: " + event.getPedidoId() + " correlationId=" + correlationId);
    }
}
