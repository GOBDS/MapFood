package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

    private MotoboyRepository repository;

    @Autowired
    public LogisticService(MotoboyRepository repository) {
        this.repository = repository;
    }

    public MotoboyModel createMotoboy(MotoboyModel motoboy) {
        return repository.save(motoboy);
    }


    public GeoResults<MotoboyModel> getNearestMotoboy(double latitute, double longitude) {
        return repository.findByPositionNear(new Point(latitute, longitude), new Distance(10, Metrics.KILOMETERS));
    }

    public void startPlanning(OrderModel order) {

    }
}

