package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel, Integer> {
}
