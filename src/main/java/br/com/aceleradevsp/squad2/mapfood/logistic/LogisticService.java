package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.AuthenticationController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.JobController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.PostProblemController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.SolutionController;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Job;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Points;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.PostObject;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Solution;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions.ObjectNotFoundException;
import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogisticService {

    private MotoboyRepository repository;
    private PostProblemController problemController;
    private AuthenticationController authController;
    private JobController jobController;
    private SolutionController solutionController;

    public MotoboyModel createMotoboy(MotoboyModel motoboy) {
        return repository.save(motoboy);
    }

    @Autowired
    public LogisticService(MotoboyRepository repository, PostProblemController problemController, AuthenticationController authController, JobController jobController, SolutionController solutionController) {
        this.repository = repository;
        this.problemController = problemController;
        this.authController = authController;
        this.jobController = jobController;
        this.solutionController = solutionController;
    }

    public GeoResults<MotoboyModel> getNearestMotoboy(double latitute, double longitude) {
        return repository.findByPositionNear(new Point(latitute, longitude), new Distance(10, Metrics.KILOMETERS));
    }

    public void startPlanning(OrderModel order) {
        double[] restaurantPosition = order.getRestaurant().getPosition();
        GeoResults<MotoboyModel> nearestMotoboy = getNearestMotoboy(restaurantPosition[0], restaurantPosition[1]);

        GeoResult<MotoboyModel> geoMotoboy = nearestMotoboy.getContent()
                .stream()
                .filter(motoboyModelGeoResult -> motoboyModelGeoResult.getContent().getDelivery().size() <= 5)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum motoboy disponível no momento"));

        MotoboyModel motoboyModel = geoMotoboy.getContent();

        calculateRouteToRestaurant(order, motoboyModel);
    }

    private void calculateRouteToRestaurant(OrderModel order, MotoboyModel motoboyModel) {
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
            try {
                motoboyModel.getDelivery().add(order);
                startMonitoring(problem, motoboyModel);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getToken() {
        return authController.getTokenValid();
    }

    private void startMonitoring(PostObject problem, MotoboyModel motoboyModel) throws InterruptedException {
        int percent = 0;
        Job job;
        while (percent != 100) {
            job = jobController.getJobById(getToken(), problem.getId());
            percent = Integer.parseInt(job.getPercent());
            Thread.sleep(2000);
        }
        getSolution(problem, motoboyModel);
    }

    private void getSolution(PostObject problem, MotoboyModel motoboyModel) {
        Solution solution = solutionController.getSolutionById(getToken(), problem.getId());
        if (solution.getId() != null) {
            motoboyModel.getRoutes().add(solution);
        }
    }
}

