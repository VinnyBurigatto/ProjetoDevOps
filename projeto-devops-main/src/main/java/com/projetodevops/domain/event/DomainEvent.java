package com.projetodevops.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {

    UUID getEventId();
    String getTipoEvento();
    LocalDateTime getOcorridoEm();

}
