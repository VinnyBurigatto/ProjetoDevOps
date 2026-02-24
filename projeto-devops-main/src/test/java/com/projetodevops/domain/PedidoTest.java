package com.projetodevops.domain;

import org.junit.jupiter.api.Test;

import com.projetodevops.domain.Item;
import com.projetodevops.domain.Pedido;
import com.projetodevops.domain.StatusPedido;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

class PedidoTest {

    @Test
    void deveNascerComStatusPendente() {
        
        Item item = new Item(
    "Mouse#1",
    "Mouse Corsair",
    1,
    new BigDecimal("99.00")
);

    List<Item> itens = List.of(item);
    
    Pedido pedido = new Pedido("Vinicius", itens);
    
    assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
    
}

    @Test
    void naoDevePermitirProcessarDuasVezes() {
        Item item = new Item(
    "Mouse#1",
    "Mouse Corsair",
    1,
    new BigDecimal("99.00")
);

    List<Item> itens = List.of(item);
    
    Pedido pedido = new Pedido("Vinicius", itens);
    
    pedido.processar();
    
    assertThrows(IllegalStateException.class, () -> {pedido.processar();});
    
}

    @Test
    void naoDevePermitirCancelarPedidoProcessado() {
        Item item = new Item(
    "Mouse#1",
    "Mouse Corsair",
    1,
    new BigDecimal("99.00")
);

    List<Item> itens = List.of(item);
    
    Pedido pedido = new Pedido("Vinicius", itens);
    
    pedido.processar();
    
    assertThrows(IllegalStateException.class, pedido::cancelar);
    
}

}