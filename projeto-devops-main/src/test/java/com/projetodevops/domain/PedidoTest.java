package com.projetodevops.domain;

import org.junit.jupiter.api.Test;

import com.projetodevops.domain.Item;
import com.projetodevops.domain.Pedido;
import com.projetodevops.domain.StatusPedido;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

class PedidoTest {

    private Pedido criarPedidoValido() {
        Item item = new Item("Mouse#1",
        "Mouse Corsair",
        1,
        new BigDecimal("99.00"));

        List<Item> itens = List.of(item);
            return new Pedido("Vinícius", itens);
    }

    @Test
    void deveNascerComStatusPendente() {
        Pedido pedido = criarPedidoValido();
    
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
    
}

    @Test
    void naoDevePermitirProcessarDuasVezes() {
        Pedido pedido = criarPedidoValido();
    
        pedido.processar();
    
        assertThrows(IllegalStateException.class, () -> {pedido.processar();});
    
}

    @Test
    void naoDevePermitirCancelarPedidoProcessado() {
        Pedido pedido = criarPedidoValido();
    
        pedido.processar();
    
        assertThrows(IllegalStateException.class, pedido::cancelar);
    
}

    @Test
    void deveProcessarPedidoComSucesso() {
        Pedido pedido = criarPedidoValido();

        pedido.processar();

        assertEquals(StatusPedido.PROCESSADO, pedido.getStatus());
    }

    @Test
    void deveCancelarPedidoComSucesso() {
        Pedido pedido = criarPedidoValido();

        pedido.cancelar();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());

    }

    @Test
    void naoDevePermitirProcessarPedidoCancelado() {
        Pedido pedido = criarPedidoValido();

        pedido.cancelar();

        assertThrows(IllegalStateException.class, pedido::processar);
    }

    @Test
    void naoDevePermitirCancelarPedidoCancelado() {
        Pedido pedido = criarPedidoValido();

        pedido.cancelar();

        assertThrows(IllegalStateException.class, pedido::cancelar);
        

    }

}