package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class OrderModel {

    @Id
    private String orderId;
    private ClientModel client;
    private RestaurantModel restaurant;
    private ItemModel product;

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
}
