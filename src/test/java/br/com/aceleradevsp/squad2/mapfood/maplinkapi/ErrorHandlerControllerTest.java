package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ErrorHandlerControllerTest {

    ErrorHandlerController errorHandlerController;

    ClientHttpResponse mockResponse = mock(ClientHttpResponse.class);

    @Before
    public void init(){
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
        errorHandlerController.handleError(URI.create(""), HttpMethod.GET,mockResponse);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void assertThatHandlerErrorObjectNotFound() throws IOException {
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        errorHandlerController.handleError(URI.create(""), HttpMethod.GET,mockResponse);
    }
}
