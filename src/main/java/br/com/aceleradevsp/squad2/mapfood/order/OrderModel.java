package br.com.aceleradevsp.squad2.mapfood.order;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.aceleradevsp.squad2.mapfood.utils.MapFoodUtils;

@Document(collection = "order")
public class OrderModel {

    @Id
    private String orderId;
    private ClientModel client;
    private RestaurantModel restaurant;
    private ItemModel product;
    private LocalDate date;

    public OrderModel(){
    }

    public OrderModel(String orderId, ClientModel client, RestaurantModel restaurant, ItemModel product, LocalDate date) {
        this.orderId = orderId;
        this.client = client;
        this.restaurant = restaurant;
        this.product = product;
        this.date = date;
    }

    public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        
    	
    	this.orderId = orderId;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public ItemModel getProduct() {
        return product;
    }

    public void setProduct(ItemModel product) {
        this.product = product;
    }

    public static OrderModelBuilder builder(){
        return new OrderModelBuilder();
    }

    public static class OrderModelBuilder {
        private String orderId;
        private ClientModel client;
        private RestaurantModel restaurant;
        private ItemModel product;
        private LocalDate date;

        public OrderModelBuilder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderModelBuilder withClient(ClientModel client) {
        	this.client = client;
            return this;
        }

        public OrderModelBuilder withRestaurant(RestaurantModel restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public OrderModelBuilder withProduct(ItemModel product) {
            this.product = product;
            return this;
        }
        
        public OrderModelBuilder withProduct(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderModel build() {
            return new OrderModel(orderId, client, restaurant, product, date);
        }

    }
}
