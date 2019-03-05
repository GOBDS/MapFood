package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class ErrorHandlerController implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            throw new RuntimeException("Token expirado");
        } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new RuntimeException("Objeto não encontrado");
        }
    }
}
