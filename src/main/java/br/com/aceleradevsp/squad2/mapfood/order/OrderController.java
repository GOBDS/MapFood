package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderModel> aNewOrder(@RequestBody OrderModel order) {
        OrderModel orderReceived = service.postOrder(order);
        if (orderReceived.getOrderId() != null) {
            return ResponseEntity.ok(orderReceived);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantModel>> getRestaurants() {
        return ResponseEntity.ok(service.allRestaurants());
    }


}
