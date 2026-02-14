package com.projetodevops.domain;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pedido {
    
    private UUID id;
    private String cliente;
    private List<Item> itens;
    private StatusPedido status;
    private LocalDateTime dataHora;

    public Pedido(String cliente, List<Item> itens){
       
        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("O pedido de ter pelo menos um item!");
    }

    public void processar() {
       
        if (status == StatusPedido.PROCESSADO) {
            throw new IllegalArgumentException("Pedido já PROCESSADO!");
        }

        if (status == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Pedido CANCELADO não pode ser processado.");
        }

        this.status = StatusPedido.PROCESSADO;
    }

    public void cancelar() {

        if (status == StatusPedido.PROCESSADO) {
            throw new IllegalArgumentException("Pedido não pode ser CANCELADO, já está processado!");
        }

        if (status == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Pedido já está CANCELADO!");
        }

        this.status = StatusPedido.CANCELADO;
    }
}
