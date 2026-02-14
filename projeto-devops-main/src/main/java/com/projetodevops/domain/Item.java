package com.projetodevops.domain;

import java.math.BigDecimal;

public class Item {
    
    private String produtoId;
    private String nomeProduto;
    private int quantidade;
    private BigDecimal precoUnitario;

    public Item(String produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario){

        if (produtoId == null || produtoId.trim().isEmpty()) {
            throw new IllegalArgumentException("Produto inválido");
        }

        if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto inválido");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de ser maior que zero");
        }

         if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Preço Unitário Inválido");
        }

        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

}
