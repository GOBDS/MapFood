package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Solution;
import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("delivers")
public class DeliverModel {

    @Id
    private String id;

    private OrderModel orderModel;

    private Solution routeToRestaurant;

    private Solution routeToClient;

    private Integer totalDistance;

    private Integer totalTime;

    public DeliverModel() {
    }

    public DeliverModel(String id, OrderModel orderModel, Solution routeToRestaurant, Solution routeToClient, Integer totalDistance, Integer totalTime) {
        this.id = id;
        this.orderModel = orderModel;
        this.routeToRestaurant = routeToRestaurant;
        this.routeToClient = routeToClient;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public Solution getRouteToRestaurant() {
        return routeToRestaurant;
    }

    public void setRouteToRestaurant(Solution routeToRestaurant) {
        this.routeToRestaurant = routeToRestaurant;
    }

    public Solution getRouteToClient() {
        return routeToClient;
    }

    public void setRouteToClient(Solution routeToClient) {
        this.routeToClient = routeToClient;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "DeliverModel{" +
                "id='" + id + '\'' +
                ", orderModel=" + orderModel +
                ", routeToRestaurant=" + routeToRestaurant +
                ", routeToClient=" + routeToClient +
                ", totalDistance=" + totalDistance +
                ", totalTime=" + totalTime +
                '}';
    }
}
