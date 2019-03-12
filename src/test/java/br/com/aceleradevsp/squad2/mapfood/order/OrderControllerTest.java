package br.com.aceleradevsp.squad2.mapfood.order;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataMongoTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void receivingAnOrder() {
        ClientModel client = MapFoodUtils.randomClient(clientRepository.findAll());
        RestaurantModel restaurant = MapFoodUtils.randomRestaurant(restaurantRepository.findAll());

        List<ItemModel> products = new ArrayList<>();
        ItemModel itemModel = restaurant.getMenu().get(MapFoodUtils.getRandomNumber(0, restaurant.getMenu().size() - 1));
        products.add(itemModel);

        OrderModel order = new OrderModel();
        order.setClient(client);
        order.setRestaurant(restaurant);
        order.setProducts(products);
        order.setDate(LocalDate.now().toString());

        try {
            ResponseEntity<OrderModel> responseEntity = restTemplate.postForEntity(URI.create("http://localhost:" + port + "/orders"), order, OrderModel.class);

            assertNotNull(responseEntity);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
