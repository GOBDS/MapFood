package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private ClientRepository clientRepo;
    private OrderRepository orderRepo;
    private RestaurantRepository restaurantRepo;

    @Autowired
    public OrderService(ClientRepository clientRepo, OrderRepository orderRepo, RestaurantRepository restaurantRepo){
        this.clientRepo = clientRepo;
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public ClientModel createClient(ClientModel client){
        return clientRepo.save(client);
    }

    public RestaurantModel createRestaurante(RestaurantModel restaurant) {
        return restaurantRepo.save(restaurant);
    }
}
