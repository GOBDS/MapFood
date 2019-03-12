package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.AuthenticationController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.JobController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.PostProblemController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.SolutionController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Job;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Points;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.PostObject;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Solution;
import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlanRoutes {
    Logger logger = LoggerFactory.getLogger(PlanRoutes.class);

    private PostProblemController problemController;
    private AuthenticationController authController;
    private JobController jobController;
    private SolutionController solutionController;
    private MotoboyRepository repository;

    private LogisticService service;

    private DeliverModel deliver;

    @Autowired
    public PlanRoutes(PostProblemController problemController, AuthenticationController authController, JobController jobController, SolutionController solutionController, MotoboyRepository repository) {
        this.problemController = problemController;
        this.authController = authController;
        this.jobController = jobController;
        this.solutionController = solutionController;
        this.repository = repository;
    }

    public GeoResults<MotoboyModel> getNearestMotoboy(double latitute, double longitude) {
        return repository.findByPositionNear(new Point(latitute, longitude), new Distance(10, Metrics.KILOMETERS));
    }

    private String getToken() {
        return authController.getTokenValid();
    }

    public void startPlanningToRestaurant(OrderModel order, LogisticService service) throws InterruptedException {
        this.service = service;
        double[] restaurantPosition = order.getRestaurant().getPosition();
        GeoResults<MotoboyModel> nearestMotoboy = getNearestMotoboy(restaurantPosition[0], restaurantPosition[1]);
        int times = 1;
        Optional<GeoResult<MotoboyModel>> geoMotoboy = Optional.empty();
        Optional<GeoResult<MotoboyModel>> geoLastMotoboy = Optional.empty();
        while (times <= 10) {
            if (geoMotoboy.isPresent()) {
                geoLastMotoboy = geoMotoboy;
            }
            geoMotoboy = nearestMotoboy.getContent()
                    .stream()
                    .filter(motoboyModelGeoResult -> motoboyModelGeoResult.getContent().getDeliveries().size() <= 5)
                    .findFirst();
            Thread.sleep(60000);
            times++;
        }

        MotoboyModel motoboyModel = null;
        if (geoMotoboy.isPresent()) {
            motoboyModel = geoMotoboy.get().getContent();
        } else if (geoLastMotoboy.isPresent()) {
            motoboyModel = geoLastMotoboy.get().getContent();
        }
        if (motoboyModel != null) {
            calculateRouteToRestaurant(order, motoboyModel);
        }
    }

    private void calculateRouteToRestaurant(OrderModel order, MotoboyModel motoboyModel) throws InterruptedException {
        Points restaurantLocation =
                new Points(order.getRestaurant().getPosition()[0], order.getRestaurant().getPosition()[1]);
        Points motoboyLocation =
                new Points(motoboyModel.getPosition()[0], motoboyModel.getPosition()[1]);

        List<Points> points = new ArrayList<>();
        points.add(motoboyLocation);
        points.add(restaurantLocation);

        PostObject object = new PostObject(points);

        PostObject problem = problemController.sendProblem(object, getToken());

        if (problem.getId() != null) {
            deliver.setOrderModel(order);
            startMonitoringRestaurant(problem, motoboyModel);
        }
    }

    private void startMonitoringRestaurant(PostObject problem, MotoboyModel motoboyModel) throws InterruptedException {
        int percent = 0;
        Job job;
        while (percent != 100) {
            job = jobController.getJobById(getToken(), problem.getId());
            percent = Integer.parseInt(job.getPercent());
            Thread.sleep(20000);
        }
        getSolutionForRestaurant(problem, motoboyModel);
    }

    private void getSolutionForRestaurant(PostObject problem, MotoboyModel motoboyModel) throws InterruptedException {
        Solution solution = solutionController.getSolutionById(getToken(), problem.getId());
        if (solution.getId() != null) {
            deliver.setRouteToRestaurant(solution);
            deliver.setTotalDistance(solution.getTotalDistance());
            deliver.setTotalTime(solution.getTotalNominalDuration());
            calculateRouteToDeliver(deliver.getOrderModel(), motoboyModel);
        }
    }

    private void calculateRouteToDeliver(OrderModel order, MotoboyModel motoboyModel) throws InterruptedException {
        Points restaurantLocation =
                new Points(order.getRestaurant().getPosition()[0], order.getRestaurant().getPosition()[1]);
        Points deliverLocation =
                new Points(order.getClient().getPosition()[0], order.getClient().getPosition()[1]);

        List<Points> points = new ArrayList<>();
        points.add(deliverLocation);
        points.add(restaurantLocation);

        PostObject object = new PostObject(points);

        PostObject problem = problemController.sendProblem(object, getToken());

        if (problem.getId() != null) {
            startMonitoringDeliver(problem, motoboyModel);
        }
    }

    private void startMonitoringDeliver(PostObject problem, MotoboyModel motoboyModel) throws InterruptedException {
        int percent = 0;
        Job job;
        while (percent != 100) {
            job = jobController.getJobById(getToken(), problem.getId());
            percent = Integer.parseInt(job.getPercent());
            Thread.sleep(20000);
        }
        getSolutionForDeliver(problem, motoboyModel);
    }

    private void getSolutionForDeliver(PostObject problem, MotoboyModel motoboyModel) {
        Solution solution = solutionController.getSolutionById(getToken(), problem.getId());
        if (solution.getId() != null) {
            deliver.setRouteToClient(solution);
            deliver.setTotalDistance(deliver.getTotalDistance() + solution.getTotalDistance());
            deliver.setTotalTime(deliver.getTotalTime() + solution.getTotalNominalDuration());
            motoboyModel.getDeliveries().add(deliver);
            service.updateMotoboy(motoboyModel);
        }
    }
}
