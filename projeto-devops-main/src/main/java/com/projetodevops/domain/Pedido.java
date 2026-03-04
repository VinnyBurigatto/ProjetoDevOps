package com.projetodevops.domain;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pedido {
    
    private final UUID id;
    private final String cliente;
    private final List<Item> itens;
    private StatusPedido status;
    private final LocalDateTime dataHora;

    public Pedido(String cliente, List<Item> itens){
    
        if (cliente == null || cliente.trim().isEmpty())
            throw new IllegalArgumentException("Nome do cliente inválido");

        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("O pedido de ter pelo menos um item!");
    
            this.id = UUID.randomUUID();
            this.cliente = cliente;
            this.itens = List.copyOf(itens);
            this.status = StatusPedido.PENDENTE;
            this.dataHora = LocalDateTime.now();
    }

    public Pedido(UUID id, String cliente, List<Item> itens, StatusPedido status, LocalDateTime dataHora) {

        this.id = id;
        this.cliente = cliente;
        this.itens = List.copyOf(itens);
        this.status = status;
        this.dataHora = dataHora;
    }

    public void processar() {
        this.status = this.status.processar();
    }

    public void cancelar() {
        this.status = this.status.cancelar();
    }

    public UUID getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

}
