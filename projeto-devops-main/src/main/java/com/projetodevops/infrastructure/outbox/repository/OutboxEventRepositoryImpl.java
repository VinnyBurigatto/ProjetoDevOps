package com.projetodevops.infrastructure.outbox.repository;

import com.projetodevops.infrastructure.outbox.OutboxEvent;
import com.projetodevops.infrastructure.outbox.OutboxEventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import java.util.Optional;

@Repository
public class OutboxEventRepositoryImpl implements OutboxEventRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<OutboxEvent> findNextPendingEventAndMarkAsProcessing() {
        Query query = new Query(Criteria.where("status").is(OutboxEventStatus.PENDENTE));

        Update update = new Update().set("status", OutboxEventStatus.PROCESSANDO);

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);

        OutboxEvent event = mongoTemplate.findAndModify(query, update, options, OutboxEvent.class);
        
        return Optional.ofNullable(event);

    }

}