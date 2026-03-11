package com.projetodevops.service;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoCriadoConsumer {

    @KafkaListener(topics = "pedidos-criados", groupId = "grupo-pedidos")
    public void consumir(PedidoCriadoEvent event) {
        System.out.println("Pedido criado recebido: " + event.getPedidoId());
    }
}
