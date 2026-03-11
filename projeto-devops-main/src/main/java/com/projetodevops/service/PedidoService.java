package com.projetodevops.service;

import com.projetodevops.domain.Item;
import com.projetodevops.domain.Pedido;
import com.projetodevops.domain.PedidoRepository;
import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.domain.event.PedidoEventPublisher;
import com.projetodevops.dto.CriarPedidoRequest;
import com.projetodevops.dto.ItemRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoEventPublisher pedidoEventPublisher;

    public PedidoService(PedidoRepository pedidoRepository, PedidoEventPublisher pedidoEventPublisher, KafkaPedidoEventPublisher kafkaPedidoEventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoEventPublisher = pedidoEventPublisher;
    }

    public Pedido criarPedido(CriarPedidoRequest request) {
        
        List<ItemRequest> itensRequest = request.getItens();
        List<Item> itens = new ArrayList<>();

        for (ItemRequest itemRequest : itensRequest) {
            Item item = new Item(itemRequest.getProdutoId(), itemRequest.getNomeProduto(), itemRequest.getQuantidade(), itemRequest.getPrecoUnitario());

            itens.add(item);
        }

        Pedido pedido = new Pedido(request.getCliente(), itens);
        
        Pedido pedidoSalvo = pedidoRepository.salvar(pedido);

        PedidoCriadoEvent event = new PedidoCriadoEvent(pedidoSalvo.getId(), pedidoSalvo.getCliente(), pedidoSalvo.getDataHora());

        pedidoEventPublisher.publicar(event);

        return pedidoSalvo;
    }
}
