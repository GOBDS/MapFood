package br.com.aceleradevsp.squad2.mapfood.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    ManagerService service;

    @Autowired
    public ManagerController(ManagerService service) {
        this.service = service;
    }
}
