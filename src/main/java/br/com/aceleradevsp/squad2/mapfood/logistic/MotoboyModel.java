package br.com.aceleradevsp.squad2.mapfood.logistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "motoboy")
public class MotoboyModel {

    @Id
    private Integer idMotoBoy;

    @GeoSpatialIndexed
    private double[] position;

    private List<DeliverModel> deliveries;

    public MotoboyModel() {
    }

    public MotoboyModel(Integer idMotoBoy, double[] position, List<DeliverModel> deliveries) {
        this.idMotoBoy = idMotoBoy;
        this.position = position;
        this.deliveries = deliveries;
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

    @Override
    public String toString() {
        return "MotoboyModel{" +
                "idMotoBoy=" + idMotoBoy +
                ", position=" + Arrays.toString(position) +
                ", deliveries=" + deliveries +
                '}';
    }

    public List<DeliverModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliverModel> deliveries) {
        this.deliveries = deliveries;
    }

    public static class MotoboyModelBuilder {
        private Integer idMotoBoy;

        @GeoSpatialIndexed
        private double[] position;

        private List<DeliverModel> deliveries;

        public MotoboyModelBuilder withIdMotoBoy(Integer idMotoBoy) {
            this.idMotoBoy = idMotoBoy;
            return this;
        }

        public MotoboyModelBuilder withPosition(double[] position) {
            this.position = position;
            return this;
        }

        public MotoboyModelBuilder withDelivery(List<DeliverModel> deliveries) {
            this.deliveries = deliveries;
            return this;
        }

        public MotoboyModel build() {
            return new MotoboyModel(idMotoBoy, position, deliveries);
        }
    }
}
