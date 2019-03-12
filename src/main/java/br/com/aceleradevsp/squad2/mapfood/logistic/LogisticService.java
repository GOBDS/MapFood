package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

    Logger logger = LoggerFactory.getLogger(LogisticService.class);

    private MotoboyRepository repository;
    private PlanRoutes planRoutes;

    public MotoboyModel createMotoboy(MotoboyModel motoboy) {
        return repository.save(motoboy);
    }

    @Autowired
    public LogisticService(MotoboyRepository repository, PlanRoutes planRoutes) {
        this.repository = repository;
        this.planRoutes = planRoutes;
    }

    public void startPlans(OrderModel order) {
        try {
            planRoutes.startPlanningToRestaurant(order, this);
        } catch (InterruptedException e) {
           logger.error(e.getMessage());
        }
    }

    public GeoResults<MotoboyModel> getNearestMotoboy(double latitute, double longitude){
        return repository.findByPositionNear(new Point(latitute, longitude), new Distance(10, Metrics.KILOMETERS));
    }

    public void updateMotoboy(MotoboyModel motoboyModel) {
        //Implements a send to motoboy.
    }

}

