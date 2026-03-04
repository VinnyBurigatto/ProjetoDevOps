package com.projetodevops.repository.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import com.projetodevops.domain.Item;
import com.projetodevops.domain.Pedido;
import com.projetodevops.domain.StatusPedido;
import com.projetodevops.repository.document.PedidoDocument;

@Component

public class PedidoMapper {
    
    private final ItemMapper itemMapper;

    public PedidoMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public PedidoDocument toDocument(Pedido pedido) {

        PedidoDocument document = new PedidoDocument();

        document.setId(pedido.getId().toString());
        document.setCliente(pedido.getCliente());
        document.setStatus(pedido.getStatus().name());
        document.setDataHora(pedido.getDataHora());
        document.setItens(pedido.getItens().stream().map(itemMapper::toDocument).toList());

        return document;
    }

    public Pedido toDomain(PedidoDocument document) {
        
        UUID id = UUID.fromString(document.getId());
        
        StatusPedido status = StatusPedido.valueOf(document.getStatus());
        
        List<Item> itens = document.getItens().stream().map(itemMapper::toDomain).toList();
            return new Pedido(id, document.getCliente(), itens, status, document.getDataHora());
    }
        
}
