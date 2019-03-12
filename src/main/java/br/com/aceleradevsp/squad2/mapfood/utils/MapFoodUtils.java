package br.com.aceleradevsp.squad2.mapfood.utils;

import br.com.aceleradevsp.squad2.mapfood.order.ClientModel;
import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import br.com.aceleradevsp.squad2.mapfood.order.OrderService;
import br.com.aceleradevsp.squad2.mapfood.order.RestaurantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MapFoodUtils {

    @Autowired
    OrderService service;

    public static int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static RestaurantModel randomRestaurant(List<RestaurantModel> restaurantList) {
        RestaurantModel restaurantOrder = new RestaurantModel();
        if (!restaurantList.isEmpty()) {
            int indice = MapFoodUtils.getRandomNumber(0, 10);
            restaurantOrder = restaurantList.get(indice);
        }
        return restaurantOrder;
    }

    public static ClientModel randomClient(List<ClientModel> clientList) {
        ClientModel clientOrder = new ClientModel();
        if (!clientList.isEmpty()) {
            int indice = MapFoodUtils.getRandomNumber(0, 10);
            clientOrder = clientList.get(indice);
        }
        return clientOrder;
    }

    public void populateOrders() {

        List<RestaurantModel> restaurantList;
        restaurantList = service.allRestaurants();

        List<ClientModel> clientList;
        clientList = service.allClients();

        RestaurantModel restaurant = randomRestaurant(restaurantList);

        ClientModel client = randomClient(clientList);

        OrderModel order = new OrderModel();
        order.setRestaurant(restaurant);
        order.setClient(client);
        service.save(order);


    }
}
