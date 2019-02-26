package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel,String> {
}
