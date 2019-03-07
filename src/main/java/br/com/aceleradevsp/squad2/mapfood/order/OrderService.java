package br.com.aceleradevsp.squad2.mapfood.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;

@Service
public class OrderService{

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
    
    public List <ClientModel> allClients() {
    	List <ClientModel> clientList = new ArrayList<>();
    	clientList = clientRepo.findAll();
    	return clientList;
    }
    
    public List <RestaurantModel> allRestaurants() {
    	List <RestaurantModel> restaurantList = new ArrayList<>();
    	restaurantList = restaurantRepo.findAll();
    	return restaurantList;
    }
   
    
   /* public LocalDateTime randomDate() {
    	return MapFoodUtils.getRandonDate(LocalDateTime.now());
    }*/  
    
    
}
