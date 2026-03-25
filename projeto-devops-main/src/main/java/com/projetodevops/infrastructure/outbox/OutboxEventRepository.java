package com.projetodevops.infrastructure.outbox;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetodevops.infrastructure.outbox.repository.OutboxEventRepositoryCustom;

import java.util.List;

public interface OutboxEventRepository extends MongoRepository<OutboxEvent, String>, OutboxEventRepositoryCustom {

    List<OutboxEvent> findByStatus(OutboxEventStatus status);

}
