package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.ItemModel;
import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "motoboy")
public class MotoboyModel {

    @Id
    private Integer idMotoBoy;
    private Position postiion;
    private List<ItemModel> delivery;


    public MotoboyModel(){
    }

    public MotoboyModel(Integer idMotoBoy, Position postiion, List<ItemModel> delivery) {
        this.idMotoBoy = idMotoBoy;
        this.postiion = postiion;
        this.delivery = delivery;
    }

    public Integer getIdMotoBoy() {
        return idMotoBoy;
    }

    public void setIdMotoBoy(Integer idMotoBoy) {
        this.idMotoBoy = idMotoBoy;
    }

    public Position getPostiion() {
        return postiion;
    }

    public void setPostiion(Position postiion) {
        this.postiion = postiion;
    }

    public List<ItemModel> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<ItemModel> delivery) {
        this.delivery = delivery;
    }

    public static MotoboyModelBuilder builder(){
        return new MotoboyModelBuilder();
    }


    public static class MotoboyModelBuilder {
        private Integer idMotoBoy;
        private Position postiion;
        private List<ItemModel> delivery;

        public MotoboyModelBuilder withIdMotoBoy(Integer idMotoBoy) {
            this.idMotoBoy = idMotoBoy;
            return this;
        }

        public MotoboyModelBuilder withPostiion(Position postiion) {
            this.postiion = postiion;
            return this;
        }

        public MotoboyModelBuilder withDelivery(List<ItemModel> delivery) {
            this.delivery = delivery;
            return this;
        }

        public MotoboyModel build() {
            return new MotoboyModel(idMotoBoy, postiion, delivery);
        }
    }
}
