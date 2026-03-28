package com.projetodevops.infrastructure.messaging.idempotency;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessedEventRepository extends MongoRepository<ProcessedEvent, String> {

    Optional<ProcessedEvent> findByEventId(UUID eventId);

}
