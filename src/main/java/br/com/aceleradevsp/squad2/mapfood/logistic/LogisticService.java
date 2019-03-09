package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

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
        planRoutes.startPlanningToRestaurant(order);
    }

    public void updateMotoboy(MotoboyModel motoboyModel) {
        //Implements a send to motoboy.

    }

}

