package com.projetodevops.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "event_dlq_audit")
public class EventDLQAuditDocument {

    @Id
    private String id;
    private String topic;
    private Integer partition;
    private Long offset;
    private String payload;
    private String stacktrace;
    private LocalDateTime createdAt;

    public EventDLQAuditDocument(String topic, Integer partition, Long offset, String payload, String stacktrace, LocalDateTime createdAt) {
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
        this.payload = payload;
        this.stacktrace = stacktrace;
        this.createdAt = createdAt;

    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public Long getOffset() {
        return offset;
    }

    public String getPayload() {
        return payload;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
