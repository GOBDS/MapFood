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

    public OrderModel(){
    }

    public OrderModel(String orderId, ClientModel client, RestaurantModel restaurant, ItemModel product) {
        this.orderId = orderId;
        this.client = client;
        this.restaurant = restaurant;
        this.product = product;
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

    private static class OrderModelBuilder {
        private String orderId;
        private ClientModel client;
        private RestaurantModel restaurant;
        private ItemModel product;

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

        public OrderModel build() {
            return new OrderModel(orderId, client, restaurant, product);
        }
    }
}
