package br.com.aceleradevsp.squad2.mapfood.OrdersTest;

import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
import br.com.aceleradevsp.squad2.mapfood.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersConstructTest {
	@Autowired
	MapFoodUtils utils;

	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void ordersConstruct() {
		utils.populateOrders();
		List<OrderModel> all = orderRepository.findAll();
		assertNotNull(all);
	}
	
}
