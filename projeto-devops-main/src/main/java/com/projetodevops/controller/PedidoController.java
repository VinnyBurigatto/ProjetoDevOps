package com.projetodevops.controller;

import org.springframework.web.bind.annotation.*;

import com.projetodevops.dto.CriarPedidoRequest;
import com.projetodevops.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
        private PedidoService pedidoService;

    @PostMapping
        public String criarPedido(@RequestBody CriarPedidoRequest request) {
            pedidoService.criarPedido(request);
                return "Pedido recebido para cliente: " + request.getCliente();
        }

}
