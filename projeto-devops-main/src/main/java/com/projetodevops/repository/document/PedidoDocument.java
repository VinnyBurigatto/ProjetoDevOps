package com.projetodevops.repository.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.time.LocalDateTime;

@Document(collection = "pedidos")
public class PedidoDocument {

    @Id
    private String id;
    private String cliente;
    private List<ItemDocument> itens;
    private String status;
    private LocalDateTime dataHora;

    public PedidoDocument () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ItemDocument> getItens() {
        return itens;
    }

    public void setItens(List<ItemDocument> itens) {
        this.itens = itens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

}