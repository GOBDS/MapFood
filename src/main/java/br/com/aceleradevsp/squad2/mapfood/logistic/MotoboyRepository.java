package br.com.aceleradevsp.squad2.mapfood.logistic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoboyRepository extends MongoRepository<MotoboyModel,Integer> {
}
