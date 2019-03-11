package br.com.aceleradevsp.squad2.mapfood.motosimulator;

import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.TimerTask;

public class UpdateMotoboy extends TimerTask {

    Integer idMotoboy;
    Double latitude;
    Double longitude;
    MongoTemplate mongoTemplate;

    public UpdateMotoboy(MongoTemplate mongoTemplate, Integer idMotoboy, Double latitute, Double longitude){
        this.idMotoboy = idMotoboy;
        this.latitude = latitute;
        this.longitude = longitude;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run() {
        MotoboyModel motoboyModel = mongoTemplate.findById(idMotoboy,MotoboyModel.class);
        double[] position = new double[2];
        position[0] = latitude;
        position[1] = longitude;
        motoboyModel.setPosition(position);
        mongoTemplate.save(motoboyModel);
    }
}
