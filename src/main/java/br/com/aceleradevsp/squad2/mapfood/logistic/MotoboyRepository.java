package br.com.aceleradevsp.squad2.mapfood.logistic;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoboyRepository extends MongoRepository<MotoboyModel, Integer> {

    List<MotoboyModel> findByPositionNear(Position position, Distance distance);
}
