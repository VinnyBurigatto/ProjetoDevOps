package com.projetodevops.repository.mongo;

import org.springframework.stereotype.Repository;

import com.projetodevops.domain.Pedido;
import com.projetodevops.domain.PedidoRepository;
import com.projetodevops.repository.document.PedidoDocument;
import com.projetodevops.repository.mapper.PedidoMapper;


@Repository
public class PedidoRepositoryImpl implements PedidoRepository {
    private final PedidoMongoRepository pedidoMongoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoRepositoryImpl(PedidoMongoRepository pedidoMongoRepository, PedidoMapper pedidoMapper) {
        this.pedidoMongoRepository = pedidoMongoRepository;
        this.pedidoMapper = pedidoMapper;
    }

@Override
    public Pedido salvar(Pedido pedido) {
        PedidoDocument document = pedidoMapper.toDocument(pedido);
        PedidoDocument savedDocument = pedidoMongoRepository.save(document);
            return pedidoMapper.toDomain(savedDocument);
    }

}
