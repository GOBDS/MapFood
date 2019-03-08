package br.com.aceleradevsp.squad2.mapfood.order;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class ClientModel {

    @Id
    private String idClient;

    @GeoSpatialIndexed
    private Position position;

    public ClientModel() {
    }

    public ClientModel(String idClient, Position position) {
        this.idClient = idClient;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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
        private Position position;

        public ClientModelBuilder withIdClient(String idClient) {

            this.idClient = idClient;
            return this;
        }

        public ClientModelBuilder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public ClientModel build() {
            return new ClientModel(idClient, position);
        }
    }

}
