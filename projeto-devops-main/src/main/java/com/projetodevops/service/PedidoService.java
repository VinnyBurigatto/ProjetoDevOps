package com.projetodevops.service;

import com.projetodevops.domain.Item;
import com.projetodevops.domain.Pedido;
import com.projetodevops.dto.CriarPedidoRequest;
import com.projetodevops.dto.ItemRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    public void criarPedido(CriarPedidoRequest request) {
        
        List<ItemRequest> itensRequest = request.getItens();
        List<Item> itens = new ArrayList<>();

        for (ItemRequest itemRequest : itensRequest) {
            Item item = new Item(itemRequest.getProdutoId(), itemRequest.getNomeProduto(), itemRequest.getQuantidade(), itemRequest.getPrecoUnitario());

            itens.add(item);
        }

        Pedido pedido = new Pedido(request.getCliente(), itens);
    }
}
