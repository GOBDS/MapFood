package br.com.aceleradevsp.squad2.mapfood.motosimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Timer;

@Component
public class SchedulerMotoboy {

    Timer timer;

    MongoTemplate mongoTemplate;

    @Autowired
    public SchedulerMotoboy(MongoTemplate mongoTemplate, Timer timer) {
        this.mongoTemplate = mongoTemplate;
        this.timer = timer;
    }

    public void schedule(Integer idMotoboy, Double latitue, Double longitude, Duration trigger) {
        timer.schedule(new UpdateMotoboy(mongoTemplate, idMotoboy, latitue, longitude), trigger.toMillis());
    }
}
