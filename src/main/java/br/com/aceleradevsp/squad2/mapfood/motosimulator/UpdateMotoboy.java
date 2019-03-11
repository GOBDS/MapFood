package br.com.aceleradevsp.squad2.mapfood.motosimulator;

import br.com.aceleradevsp.squad2.mapfood.Application;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.TimerTask;

public class UpdateMotoboy extends TimerTask {
    Logger logger = LoggerFactory.getLogger(UpdateMotoboy.class);

    Integer idMotoboy;
    Double latitude;
    Double longitude;
    MongoTemplate mongoTemplate;

    public UpdateMotoboy(){
    }

    public UpdateMotoboy(MongoTemplate mongoTemplate, Integer idMotoboy, Double latitute, Double longitude){
        this.idMotoboy = idMotoboy;
        this.latitude = latitute;
        this.longitude = longitude;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run() {
        logger.info("Thead: " + Thread.currentThread().getId() + " Started at: " + Instant.now());
        MotoboyModel motoboyModel = mongoTemplate.findById(idMotoboy,MotoboyModel.class);
        double[] position = new double[2];
        position[0] = latitude;
        position[1] = longitude;
        motoboyModel.setPosition(position);
        mongoTemplate.save(motoboyModel);
    }
}
