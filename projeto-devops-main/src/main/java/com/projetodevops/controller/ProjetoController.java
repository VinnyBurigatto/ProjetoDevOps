package com.projetodevops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodevops.ProjetoConfig;

@RestController
public class ProjetoController {

    @Autowired
    private ProjetoConfig projetoConfig;

    // Rota raiz - remove Whitelabel
    @GetMapping("/")
    public String home() {
        return "Bem-vindo ao Projeto DevOps!";
    }

    // Rota de teste simples
    @RequestMapping("/projeto")
    public String Projeto() {
        return "Hello World";
    }

    // Rota para exibir a configuração do projeto
    @GetMapping("/config")
    public String getConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(projetoConfig.getName()).append("\n");
        sb.append("Version: ").append(projetoConfig.getVersion()).append("\n");
        sb.append("Enabled: ").append(projetoConfig.getEnabled()).append("\n");
        sb.append("Items: ").append(String.join(", ", projetoConfig.getItems())).append("\n");
        return sb.toString();
    }
}
