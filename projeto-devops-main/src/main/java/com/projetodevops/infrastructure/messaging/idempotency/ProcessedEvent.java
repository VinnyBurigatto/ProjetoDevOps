package com.projetodevops.infrastructure.messaging.idempotency;

import java.time.Instant;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "processed-events")
public class ProcessedEvent {

    @Id
    private String id;
    @Indexed(unique = true)
    private UUID eventId;
    private Instant processedAt;
    private String consumer;

    public ProcessedEvent(UUID eventId,String consumer) {
        this.eventId = eventId;
        this.consumer = consumer;
        this.processedAt = Instant.now();
    }

    public UUID getEventId() {
        return eventId;
    }

}
