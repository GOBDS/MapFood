package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.Solution;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.URLMaplink.URL_SOLUTION_BY_ID;

@Component
public class SolutionController {

    public Solution getSolutionById(String token, String id) {
        if (!id.isEmpty() && !token.isEmpty()) {
            HttpHeaders header = new HttpHeaders();
            header.setBearerAuth(token);

            RequestEntity<Solution> entity = new RequestEntity<>(header, HttpMethod.GET, URI.create(URL_SOLUTION_BY_ID.concat(id)));
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new ErrorHandlerController());

            ResponseEntity<Solution> getSolution = restTemplate.exchange(entity, Solution.class);

            if (getSolution.getStatusCode().is2xxSuccessful()) {
                return getSolution.getBody();
            }
        }
        throw new RuntimeException("O id e/ou token não pode estar em branco");
    }
}
