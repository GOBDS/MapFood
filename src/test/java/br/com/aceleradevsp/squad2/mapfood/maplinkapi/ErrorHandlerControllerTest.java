package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions.ObjectNotFoundException;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions.TokenExpiredException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ErrorHandlerControllerTest {

    ErrorHandlerController errorHandlerController;

    ClientHttpResponse mockResponse = mock(ClientHttpResponse.class);

    @Before
    public void init() {
        errorHandlerController = new ErrorHandlerController();
    }

    @Test
    public void assertThatHasError() throws IOException {
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        boolean hasError = errorHandlerController.hasError(mockResponse);
        assertTrue(hasError);
    }

    @Test(expected = TokenExpiredException.class)
    public void assertThatHandlerErrorTokenExpired() throws IOException {
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);
        errorHandlerController.handleError(URI.create(""), HttpMethod.GET, mockResponse);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void assertThatHandlerErrorObjectNotFound() throws IOException {
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        errorHandlerController.handleError(URI.create(""), HttpMethod.GET, mockResponse);
    }
}
