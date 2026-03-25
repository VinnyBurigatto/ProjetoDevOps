package com.projetodevops.infrastructure.outbox.repository;

import com.projetodevops.infrastructure.outbox.OutboxEvent;
import java.util.Optional;

public interface OutboxEventRepositoryCustom {

    Optional<OutboxEvent> findNextPendingEventAndMarkAsProcessing();

}
