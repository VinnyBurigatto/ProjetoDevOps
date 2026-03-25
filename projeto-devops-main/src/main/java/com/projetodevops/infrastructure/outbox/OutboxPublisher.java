package com.projetodevops.infrastructure.outbox;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.projetodevops.domain.event.PedidoCriadoEvent;
import com.projetodevops.domain.event.PedidoEventPublisher;
import java.util.List;

@Component
public class OutboxPublisher {

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
    List<OutboxEvent> eventosPendentes = repository.findByStatus(OutboxEventStatus.PENDENTE);
        for (OutboxEvent evento : eventosPendentes) {
            try {
                evento.marcarComoProcessando();
                repository.save(evento);

                Class<?> clazz = eventTypeMapper.getEventClass(evento.getTipoEvento());

                Object domainEvent = serializer.desserializar(evento.getPayload(), clazz);

                eventPublisher.publicar((PedidoCriadoEvent) domainEvent);

                evento.marcarComoEnviado();
                repository.save(evento);

            } catch (Exception e) {
                evento.marcarComoErro();
                evento.incrementarTentativas();
                repository.save(evento);
                e.printStackTrace();
            }
        }
    }

}
