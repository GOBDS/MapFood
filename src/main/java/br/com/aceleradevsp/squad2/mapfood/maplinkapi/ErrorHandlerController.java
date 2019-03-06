package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class ErrorHandlerController implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND || clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        //Not necessary for our purposes.
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            throw new TokenExpiredException("Token expirado");
        } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
    }
}
