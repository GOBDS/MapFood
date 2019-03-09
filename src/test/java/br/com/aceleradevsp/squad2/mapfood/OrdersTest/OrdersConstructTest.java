package br.com.aceleradevsp.squad2.mapfood.OrdersTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersConstructTest {
	@Autowired
	MapFoodUtils utils;
	
	@Test
	public void ordersConstruct() {
		utils.populateOrders();
	}
	
}
