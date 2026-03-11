package com.projetodevops.domain.event;

public interface PedidoEventPublisher {

        void publicar(PedidoCriadoEvent event);
}
