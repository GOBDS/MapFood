package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "order")
public class OrderModel {

    @Id
    private String orderId;
    private ClientModel client;
    private RestaurantModel restaurant;
    private List<ItemModel> products;
    private LocalDate date;

    public OrderModel() {
    }

    public OrderModel(String orderId, ClientModel client, RestaurantModel restaurant, List<ItemModel> products, LocalDate date) {
        this.orderId = orderId;
        this.client = client;
        this.restaurant = restaurant;
        this.products = products;
        this.date = date;
    }

    public static OrderModelBuilder builder() {
        return new OrderModelBuilder();
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

    public void setOrderId(String orderId) {


        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", client=" + client +
                ", restaurant=" + restaurant +
                ", products=" + products +
                ", date=" + date +
                '}';
    }

    public List<ItemModel> getProducts() {
        return products;
    }

    public void setProducts(List<ItemModel> products) {
        this.products = products;
    }

    public static class OrderModelBuilder {
        private String orderId;
        private ClientModel client;
        private RestaurantModel restaurant;
        private List<ItemModel> products;
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

        public OrderModelBuilder withProduct(List<ItemModel> products) {
            this.products = products;
            return this;
        }

        public OrderModelBuilder withProduct(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderModel build() {
            return new OrderModel(orderId, client, restaurant, products, date);
        }

    }
}
