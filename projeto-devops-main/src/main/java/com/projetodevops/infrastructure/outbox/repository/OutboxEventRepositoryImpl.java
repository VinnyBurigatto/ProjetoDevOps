package com.projetodevops.infrastructure.outbox.repository;

import com.projetodevops.infrastructure.outbox.OutboxEvent;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class OutboxEventRepositoryImpl implements OutboxEventRepositoryCustom {

    @Override
    public Optional<OutboxEvent> findNextPendingEventAndMarkAsProcessing() {
        return Optional.empty();
    }

}
