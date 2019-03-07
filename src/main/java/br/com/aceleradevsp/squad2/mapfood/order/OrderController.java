package br.com.aceleradevsp.squad2.mapfood.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;

@RestController
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }
}
