package com.projetodevops.dto;

import java.util.List;

public class CriarPedidoRequest {

    private String cliente;
    private List<ItemRequest> itens;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public List<ItemRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemRequest> itens) {
        this.itens = itens;
    }

}
