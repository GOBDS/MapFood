package br.com.aceleradevsp.squad2.mapfood.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class ClientModel {

    @Id
    private String idClient;

    @GeoSpatialIndexed
    private double[] position;

    public ClientModel() {
    }

    public ClientModel(String idClient, double[] position) {
        this.idClient = idClient;
        this.position = position;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public static ClientModelBuilder builder() {
        return new ClientModelBuilder();
    }

    public String getIdCliente() {
        return idClient;
    }

    public void setIdCliente(String idClient) {
        this.idClient = idClient;
    }

    public static class ClientModelBuilder {
        private String idClient;
        private double[] position;

        public ClientModelBuilder withIdClient(String idClient) {

            this.idClient = idClient;
            return this;
        }

        public ClientModelBuilder withPosition(double[] position) {
            this.position = position;
            return this;
        }

        public ClientModel build() {
            return new ClientModel(idClient, position);
        }
    }

}
