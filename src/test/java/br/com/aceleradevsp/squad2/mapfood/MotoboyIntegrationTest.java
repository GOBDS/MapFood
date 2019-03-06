package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MotoboyIntegrationTest {

    @Autowired
    MotoboyRepository repository;


    @Test
    public void assertMotoboyPopulator(){
        List<MotoboyModel> all = repository.findAll();
        assertNotNull(all);
    }

    @Test
    public void assertNearMotoboy(){
        Point point = new Point(-30.03742831, -51.228496);
        GeoResults<MotoboyModel> byPositionNear = repository.findByPositionNear(point, new Distance(5, Metrics.KILOMETERS));
        assertNotNull(byPositionNear);
        assertTrue("Assert quantity of near motoboys",byPositionNear.getContent().size() >= 1);

    }
}
