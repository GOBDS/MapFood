package br.com.aceleradevsp.squad2.mapfood.logistic;


import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoboyRepository extends MongoRepository<MotoboyModel,Integer> {

    GeoResults<MotoboyModel> findByPositionNear(Point position, Distance distance);
}
