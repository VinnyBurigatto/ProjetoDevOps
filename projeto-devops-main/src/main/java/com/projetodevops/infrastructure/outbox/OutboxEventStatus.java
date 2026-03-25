package com.projetodevops.infrastructure.outbox;

public enum OutboxEventStatus {

    PENDENTE,
    PROCESSANDO,
    ENVIADO,
    ERRO,
    ERRO_FINAL

}
