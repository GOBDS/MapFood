package br.com.aceleradevsp.squad2.mapfood.logistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticController {

    LogisticService service;

    @Autowired
    public LogisticController(LogisticService service) {
        this.service = service;
    }
}
