package com.projetodevops.controller;

import org.springframework.web.bind.annotation.*;

import com.projetodevops.dto.CriarPedidoRequest;
import com.projetodevops.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @PostMapping
        public String criarPedido(@RequestBody CriarPedidoRequest request) {
            pedidoService.criarPedido(request);
                return "Pedido recebido para cliente: " + request.getCliente();
        }

}
