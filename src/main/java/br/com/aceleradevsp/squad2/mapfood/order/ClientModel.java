package br.com.aceleradevsp.squad2.mapfood.order;

import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class ClientModel {

    @Id
    private Integer idClient;
    private Position position;

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
}
