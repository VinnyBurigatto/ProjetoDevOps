package com.projetodevops.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import java.time.LocalDateTime;

public final class PedidoCriadoEvent {

    private final UUID pedidoId;
    private final String cliente;
    private final LocalDateTime dataHora;

    @JsonCreator
    public PedidoCriadoEvent(@JsonProperty("pedidoId") UUID pedidoId, @JsonProperty("cliente") String cliente, @JsonProperty("dataHora") LocalDateTime dataHora) {
        this.pedidoId = pedidoId;
        this.cliente = cliente;
        this.dataHora = dataHora;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}