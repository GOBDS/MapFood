package br.com.aceleradevsp.squad2.mapfood.logistic;

import br.com.aceleradevsp.squad2.mapfood.order.OrderModel;
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

    private List<OrderModel> delivery;


    public MotoboyModel() {
    }

    public MotoboyModel(Integer idMotoBoy, double[] position, List<OrderModel> delivery) {
        this.idMotoBoy = idMotoBoy;
        this.position = position;
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

    public static MotoboyModelBuilder builder() {
        return new MotoboyModelBuilder();
    }

    public List<OrderModel> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<OrderModel> delivery) {
        this.delivery = delivery;
    }

    public static class MotoboyModelBuilder {
        private Integer idMotoBoy;

        @GeoSpatialIndexed
        private double[] position;

        private List<OrderModel> delivery;

        public MotoboyModelBuilder withIdMotoBoy(Integer idMotoBoy) {
            this.idMotoBoy = idMotoBoy;
            return this;
        }

        public MotoboyModelBuilder withPosition(double[] position) {
            this.position = position;
            return this;
        }

        public MotoboyModelBuilder withDelivery(List<OrderModel> delivery) {
            this.delivery = delivery;
            return this;
        }

        public MotoboyModel build() {
            return new MotoboyModel(idMotoBoy, position, delivery);
        }
    }
}
