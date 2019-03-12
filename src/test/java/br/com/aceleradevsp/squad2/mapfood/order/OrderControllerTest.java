package br.com.aceleradevsp.squad2.mapfood.order;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext appContext;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();
    }

    @Test
    public void receivingAnOrder() {
        ClientModel client = MapFoodUtils.randomClient(clientRepository.findAll());
        RestaurantModel restaurant = MapFoodUtils.randomRestaurant(restaurantRepository.findAll());

        List<ItemModel> products = new ArrayList<>();
        ItemModel itemModel = restaurant.getMenu().get(MapFoodUtils.getRandomNumber(0, restaurant.getMenu().size()));
        products.add(itemModel);

        OrderModel order = new OrderModel();
        order.setClient(client);
        order.setRestaurant(restaurant);
        order.setProducts(products);
        order.setDate(LocalDate.now());

        ObjectMapper om = new ObjectMapper();
        try {
            String jsonOrder = om.writeValueAsString(order);
            ResultActions orders = mockMvc.perform(MockMvcRequestBuilders.post("orders").content(jsonOrder).contentType(MediaType.APPLICATION_JSON));
            String response = orders.andReturn().getResponse().getContentAsString();
            OrderModel orderDone = om.readValue(response, OrderModel.class);

            Assert.assertNotNull(orderDone.getOrderId());

        } catch (Exception e) {

        }
    }
}
