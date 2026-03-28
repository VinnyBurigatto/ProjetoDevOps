package com.projetodevops.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.projetodevops.repository.document.EventDLQAuditDocument;

public interface EventDLQAuditMongoRepository extends MongoRepository<EventDLQAuditDocument, String> {

}
