package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyRepository;
import br.com.aceleradevsp.squad2.mapfood.order.ClientModel;
import br.com.aceleradevsp.squad2.mapfood.order.ClientRepository;
import br.com.aceleradevsp.squad2.mapfood.order.RestaurantModel;
import br.com.aceleradevsp.squad2.mapfood.order.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    MotoboyRepository motoboyRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void assertMotoboyPopulator(){
        List<MotoboyModel> allMotoboys = motoboyRepository.findAll();
        assertNotNull(allMotoboys);
    }

    @Test
    public void assertClientPopulator(){
        List<ClientModel> allClients = clientRepository.findAll();
        assertNotNull(allClients);
    }

    @Test
    public void assertRestaurantPopulator(){
        List<RestaurantModel> allRestaurants = restaurantRepository.findAll();
        assertNotNull(allRestaurants);
    }
}
