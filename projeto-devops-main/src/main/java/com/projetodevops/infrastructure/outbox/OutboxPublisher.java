package com.projetodevops.infrastructure.outbox;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.domain.event.PedidoEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OutboxPublisher {

    private static final Logger log = LoggerFactory.getLogger(OutboxPublisher.class);
    private final OutboxEventRepository repository;
    private final PedidoEventPublisher eventPublisher;
    private final EventSerializer serializer;
    private final EventTypeMapper eventTypeMapper;

    public OutboxPublisher(OutboxEventRepository repository, PedidoEventPublisher eventPublisher, EventSerializer serializer, EventTypeMapper eventTypeMapper) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.serializer = serializer;
        this.eventTypeMapper = eventTypeMapper;
    }

@Scheduled(fixedDelay = 5000)
public void publicarEventosPendentes() {

    log.info("Iniciando processamento de eventos do Outbox");

    int batchSize = 10;

    for (int i = 0; i < batchSize; i++) {
        var optionalEvento = repository.findNextPendingEventAndMarkAsProcessing();

        if (optionalEvento.isEmpty()) {
            break;
        }

        OutboxEvent evento = optionalEvento.get();

        log.info("Processando evento tipo={} tentativas={}", evento.getTipoEvento(), evento.getStatus());

        try {
            Class<?> clazz = eventTypeMapper.getEventClass(evento.getTipoEvento());

            PedidoCriadoEvent domainEvent = (PedidoCriadoEvent) serializer.desserializar(evento.getPayload(), clazz);

            eventPublisher.publicar(domainEvent);

            log.info("Evento enviado com sucesso tipo={}", evento.getTipoEvento());

            evento.marcarComoEnviado();

            repository.save(evento);

        } catch (Exception e) {
            evento.marcarComoErro();

            repository.save(evento);

            log.error("Erro ao publicar evento tipo={}", evento.getTipoEvento(), e);
        }

        }


    }

}
