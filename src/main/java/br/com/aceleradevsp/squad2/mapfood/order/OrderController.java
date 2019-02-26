package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }
}
