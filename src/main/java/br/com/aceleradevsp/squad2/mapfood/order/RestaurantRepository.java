package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<RestaurantModel,String> {
}