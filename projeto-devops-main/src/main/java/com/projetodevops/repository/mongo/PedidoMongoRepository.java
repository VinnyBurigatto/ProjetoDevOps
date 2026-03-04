package com.projetodevops.repository.mongo;

import com.projetodevops.repository.document.PedidoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoMongoRepository extends MongoRepository<PedidoDocument, String> {

}
