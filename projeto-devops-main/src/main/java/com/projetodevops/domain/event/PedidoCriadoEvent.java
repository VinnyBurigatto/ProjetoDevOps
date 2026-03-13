package com.projetodevops.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import java.time.LocalDateTime;

public final class PedidoCriadoEvent implements DomainEvent {

    private final UUID eventId;
    private final UUID pedidoId;
    private final String cliente;
    private final LocalDateTime ocorridoEm;

    @JsonCreator
    public PedidoCriadoEvent(@JsonProperty("pedidoId") UUID pedidoId, @JsonProperty("cliente") String cliente, @JsonProperty("ocorridoEm") LocalDateTime ocorridoEm) {
        this.eventId = UUID.randomUUID();
        this.pedidoId = pedidoId;
        this.cliente = cliente;
        this.ocorridoEm = ocorridoEm;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getTipoEvento() {
        return "PedidoCriadoEvent";
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}