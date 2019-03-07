package br.com.aceleradevsp.squad2.mapfood.logistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

    @Autowired
    private MotoboyRepository repository;

    public MotoboyModel createMotoboy(MotoboyModel motoboy) {
        return repository.save(motoboy);
    }
}
