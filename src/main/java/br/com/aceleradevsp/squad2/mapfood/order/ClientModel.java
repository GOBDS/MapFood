package br.com.aceleradevsp.squad2.mapfood.order;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class ClientModel {

    @Id
    private Integer idClient;
    private Position position;

    public ClientModel(){
    }

    public ClientModel(Integer idClient, Position position) {
        this.idClient = idClient;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getIdCliente() {
        return idClient;
    }

    public void setIdCliente(Integer idClient) {
        this.idClient = idClient;
    }

    public ClientModelBuilder builder(){
        return new ClientModelBuilder();
    }

    private static class ClientModelBuilder {
        private Integer idClient;
        private Position position;

        public ClientModelBuilder setIdClient(Integer idClient) {
            this.idClient = idClient;
            return this;
        }

        public ClientModelBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public ClientModel createClientModel() {
            return new ClientModel(idClient, position);
        }
    }

}
