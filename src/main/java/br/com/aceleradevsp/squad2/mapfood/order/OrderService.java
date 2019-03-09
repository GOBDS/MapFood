package br.com.aceleradevsp.squad2.mapfood.order;

import br.com.aceleradevsp.squad2.mapfood.logistic.LogisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private ClientRepository clientRepo;
    private OrderRepository orderRepo;
    private RestaurantRepository restaurantRepo;
    private LogisticService logisticService;

    public OrderService() {
		// TODO Auto-generated constructor stub
	}
    
    @Autowired
    public OrderService(ClientRepository clientRepo, OrderRepository orderRepo, RestaurantRepository restaurantRepo, LogisticService logisticService) {
        this.clientRepo = clientRepo;
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
        this.logisticService = logisticService;
    }

    public ClientModel createClient(ClientModel client) {
        return clientRepo.save(client);
    }

    public RestaurantModel createRestaurant(RestaurantModel restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public List<ClientModel> allClients() {
        return clientRepo.findAll();
    }

    public List<RestaurantModel> allRestaurants() {
        return restaurantRepo.findAll();
    }

    
    public OrderModel save (OrderModel order) {
    	return orderRepo.save(order);
    }
    

    public OrderModel postOrder(OrderModel order) {
        ClientModel client = createClient(order.getClient());
        RestaurantModel restaurant = createRestaurant(order.getRestaurant());

        if (!order.getProducts().isEmpty()) {
            order.setClient(client);
            order.setRestaurant(restaurant);
            OrderModel orderSaved = orderRepo.save(order);
            if (orderSaved.getOrderId() != null) {
                logisticService.startPlanning(orderSaved);
                return orderSaved;
            }
        }
        return order;
    }


   /* public LocalDateTime randomDate() {
    	return MapFoodUtils.getRandonDate(LocalDateTime.now());
    }*/


}
