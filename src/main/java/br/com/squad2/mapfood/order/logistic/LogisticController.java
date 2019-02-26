package br.com.squad2.mapfood.order.logistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticController {

    private LogisticService service;

    @Autowired
    public LogisticController(LogisticService service){
        this.service = service;
    }
}
