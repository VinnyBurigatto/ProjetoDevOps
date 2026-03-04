package com.projetodevops.repository.mapper;

import com.projetodevops.domain.Item;
import com.projetodevops.repository.document.ItemDocument;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDocument toDocument(Item item) {
        ItemDocument document = new ItemDocument();
        document.setProdutoId(item.getProdutoId());
        document.setNomeProduto(item.getNomeProduto());
        document.setQuantidade(item.getQuantidade());
        document.setPrecoUnitario(item.getPrecoUnitario());
        return document;
    }

    public Item toDomain(ItemDocument document) {
        return new Item(document.getProdutoId(), document.getNomeProduto(), document.getQuantidade(), document.getPrecoUnitario());
    }
    
}
