package com.projetodevops.domain;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pedido {
    
    private final UUID id;
    private final String cliente;
    private final List<Item> itens;
    private StatusPedido status;
    private LocalDateTime dataHora;

    public Pedido(String cliente, List<Item> itens){
    
         if (cliente == null || cliente.isBlank())
            throw new IllegalArgumentException("Nome do cliente inválido");

        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("O pedido de ter pelo menos um item!");
    
            this.id = UUID.randomUUID();
            this.cliente = cliente;
            this.itens = List.copyOf(itens);
            this.status = StatusPedido.PENDENTE;
            this.dataHora = LocalDateTime.now();
    }

    public void processar() {
       this.status = this.status.processar();
    }

    public void cancelar() {
        this.status = this.status.cancelar();
    }
}
