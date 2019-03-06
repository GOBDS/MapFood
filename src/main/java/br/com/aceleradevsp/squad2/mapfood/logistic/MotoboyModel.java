package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.ItemModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "motoboy")
public class MotoboyModel {

    @Id
    private Integer idMotoBoy;

    @GeoSpatialIndexed
    private double[] position;

    private List<ItemModel> delivery;


    public MotoboyModel(){
    }

    public MotoboyModel(Integer idMotoBoy, double[] postiion, List<ItemModel> delivery) {
        this.idMotoBoy = idMotoBoy;
        this.position = postiion;
        this.delivery = delivery;
    }

    public Integer getIdMotoBoy() {
        return idMotoBoy;
    }

    public void setIdMotoBoy(Integer idMotoBoy) {
        this.idMotoBoy = idMotoBoy;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
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

        @GeoSpatialIndexed
        private double[] position;

        private List<ItemModel> delivery;

        public MotoboyModelBuilder withIdMotoBoy(Integer idMotoBoy) {
            this.idMotoBoy = idMotoBoy;
            return this;
        }

        public MotoboyModelBuilder withPosition(double[] position) {
            this.position = position;
            return this;
        }

        public MotoboyModelBuilder withDelivery(List<ItemModel> delivery) {
            this.delivery = delivery;
            return this;
        }

        public MotoboyModel build() {
            return new MotoboyModel(idMotoBoy, position, delivery);
        }
    }
}
