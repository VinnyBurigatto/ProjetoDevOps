package com.projetodevops.infrastructure.messaging.idempotency;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class IdempotencyService {

    private final ProcessedEventRepository repository;

    public IdempotencyService(ProcessedEventRepository repository) {
        this.repository = repository;
    }

    public boolean alreadyProcessed(UUID eventId) {
        return repository.findByEventId(eventId).isPresent();
    }

    public void markProcessed(UUID eventId,String consumer) {

        repository.save(new ProcessedEvent(eventId, consumer));

    }

}
