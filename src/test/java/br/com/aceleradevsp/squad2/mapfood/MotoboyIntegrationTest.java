package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.LogisticService;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyRepository;
import br.com.aceleradevsp.squad2.mapfood.motosimulator.SchedulerMotoboy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;

import static org.hamcrest.core.Is.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MotoboyIntegrationTest {

    @Autowired
    MotoboyRepository motoboyRepository;

    @Autowired
    LogisticService service;

    @Autowired
    SchedulerMotoboy schedulerMotoboy;

    @Test
    public void assertNearMotoboy(){
        GeoResults<MotoboyModel> byPositionNear = service.getNearestMotoboy(-30.03742831, -51.228496);
        assertNotNull(byPositionNear);
        assertTrue("Assert quantity of near motoboys",byPositionNear.getContent().size() >= 1);
    }

    @Test
    public void assertTharMotoboyIsUpdated() throws InterruptedException {
        double[] position = new double[2];
        position[0] = -25.03742831;
        position[1] = -52.21598075;
        schedulerMotoboy.schedule(1, position[0], position[1], Duration.ofMillis(10L));
        Thread.sleep(100L);
        MotoboyModel motoboyModel = motoboyRepository.findById(1).orElseThrow(NullPointerException::new);
        assertThat(motoboyModel.getPosition(), is(position));
    }
}
