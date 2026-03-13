package com.projetodevops.infrastructure.outbox;

import java.time.LocalDateTime;
import java.util.UUID;

public class OutboxEvent {

    private UUID id;
    private String tipoEvento;
    private String payload;
    private OutboxEventStatus status;
    private LocalDateTime criadoEm;

}
