package com.projetodevops.infrastructure.outbox;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.projetodevops.infrastructure.tracing.CorrelationIdContext;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "outbox_events")
public class OutboxEvent {

    @Id
    private String id;
    private String tipoEvento;
    private String payload;
    private OutboxEventStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime processadoEm;
    private int tentativas;
    private String correlationId;

    public OutboxEvent() {}

    public OutboxEvent(String tipoEvento, String payload){
        this.id = UUID.randomUUID().toString();
        this.tipoEvento = tipoEvento;
        this.payload = payload;
        this.status = OutboxEventStatus.PENDENTE;
        this.criadoEm = LocalDateTime.now();
        this.tentativas = 0;
        this.processadoEm = null;
        this.correlationId = CorrelationIdContext.getCorrelationId();
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getPayload() {
        return payload;
    }

    public OutboxEventStatus getStatus() {
        return status;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void marcarComoProcessando() {
        this.status = OutboxEventStatus.PROCESSANDO;
    }

    public void marcarComoEnviado() {
        this.status = OutboxEventStatus.ENVIADO;
        this.processadoEm = LocalDateTime.now();
    }

    public void marcarComoErro() {
        this.tentativas++;
        
        if (this.tentativas >= 3) {
            
            this.status = OutboxEventStatus.ERRO_FINAL;
        
        } else {
            
            this.status = OutboxEventStatus.ERRO;
        
        }

        this.processadoEm = LocalDateTime.now();

    }

    public void incrementarTentativas() {
        this.tentativas++;
    }

}
