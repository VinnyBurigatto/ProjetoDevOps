package com.projetodevops.domain;

import com.projetodevops.domain.Pedido;

public interface PedidoRepository {

    Pedido salvar(Pedido pedido);
    
}