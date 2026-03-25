package com.projetodevops.infrastructure.outbox;

import com.projetodevops.domain.event.PedidoCriadoEvent;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventTypeMapper {

    private final Map<String, Class<?>> eventTypeMap = new HashMap<>();

    public EventTypeMapper() {
        eventTypeMap.put(PedidoCriadoEvent.class.getSimpleName(), PedidoCriadoEvent.class);
    }

    public Class<?> getEventClass(String tipoEvento) {
        Class<?> eventClass = eventTypeMap.get(tipoEvento);
            if (eventClass == null) {
                throw new RuntimeException("Tipo de evento desconhecido: " + tipoEvento);
            }

            return eventClass;

    }
    
}
