package br.com.aceleradevsp.squad2.mapfood.order;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurant")
public class RestaurantModel {

    @Id
    private String restaurantId;
    private String restaurant;
    private String adressCity;
    private Position position;
    private String dishdescription;

    public RestaurantModel() {
    }

    public RestaurantModel(String restaurantId, String restaurant, String adressCity, Position position, String dishdescription) {
        this.restaurantId = restaurantId;
        this.restaurant = restaurant;
        this.adressCity = adressCity;
        this.position = position;
        this.dishdescription = dishdescription;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getAdressCity() {
        return adressCity;
    }

    public void setAdressCity(String adressCity) {
        this.adressCity = adressCity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDishdescription() {
        return dishdescription;
    }

    public void setDishdescription(String dishdescription) {
        this.dishdescription = dishdescription;
    }

    public static RestaurantModelBuilder builder(){
        return new RestaurantModelBuilder();
    }

    private static class RestaurantModelBuilder {
        private String restaurantId;
        private String restaurant;
        private String adressCity;
        private Position position;
        private String dishdescription;

        public RestaurantModelBuilder setRestaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public RestaurantModelBuilder setRestaurant(String restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public RestaurantModelBuilder setAdressCity(String adressCity) {
            this.adressCity = adressCity;
            return this;
        }

        public RestaurantModelBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public RestaurantModelBuilder setDishdescription(String dishdescription) {
            this.dishdescription = dishdescription;
            return this;
        }

        public RestaurantModel createRestaurantModel() {
            return new RestaurantModel(restaurantId, restaurant, adressCity, position, dishdescription);
        }
    }
}
