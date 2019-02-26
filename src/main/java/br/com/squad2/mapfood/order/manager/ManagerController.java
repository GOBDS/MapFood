package br.com.squad2.mapfood.order.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    private ManagerService service;

    @Autowired
    public ManagerController(ManagerService service) {
        this.service = service;
    }
}
