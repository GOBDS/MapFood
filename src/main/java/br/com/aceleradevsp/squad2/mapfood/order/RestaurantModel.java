package br.com.aceleradevsp.squad2.mapfood.order;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "restaurant")
public class RestaurantModel {

    @Id
    private String restaurantId;

    private String restaurant;

    private String adressCity;

    @GeoSpatialIndexed
    private Position position;

    private String dishdescription;

    private List<ItemModel> menu;

    public RestaurantModel() {
    }

    public RestaurantModel(String restaurantId, String restaurant, String adressCity, Position position, String dishdescription, List<ItemModel> menu) {
        this.restaurantId = restaurantId;
        this.restaurant = restaurant;
        this.adressCity = adressCity;
        this.position = position;
        this.dishdescription = dishdescription;
        this.menu = null == menu ? new ArrayList<>() : menu;

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

    public List<ItemModel> getMenu() {
        return menu;
    }

    public void setMenu(List<ItemModel> menu) {
        this.menu = menu;
    }

    public static class RestaurantModelBuilder {

        private String restaurantId;
        private String restaurant;
        private String adressCity;
        private Position position;
        private String dishdescription;
        private List<ItemModel> menu;


        public RestaurantModelBuilder withRestaurantId(String restaurantId) {

            this.restaurantId = restaurantId;
            return this;
        }

        public RestaurantModelBuilder withRestaurant(String restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public RestaurantModelBuilder withAdressCity(String adressCity) {

            this.adressCity = adressCity;
            return this;
        }

        public RestaurantModelBuilder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public RestaurantModelBuilder withDishdescription(String dishdescription) {
            this.dishdescription = dishdescription;
            return this;
        }

        public RestaurantModelBuilder withMenu(List<ItemModel> menu){
            this.menu = menu;
            return this;
        }

        public RestaurantModel build() {
            return new RestaurantModel(restaurantId, restaurant, adressCity, position, dishdescription, menu);
        }
    }
}
