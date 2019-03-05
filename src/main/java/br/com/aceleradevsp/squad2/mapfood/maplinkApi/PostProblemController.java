package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.PostObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.URLMaplink.URL_PROBLEM;
import static java.net.URI.create;

@Component
public class PostProblemController {


    public PostObject sendProblem(PostObject object, String token) {
        if (object.getPoints().size() >= 2) {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            header.setBearerAuth(token);

            HttpEntity<PostObject> entity = new HttpEntity<>(object, header);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new ErrorHandlerController());
            ResponseEntity<PostObject> problem = restTemplate.postForEntity(create(URL_PROBLEM), entity, PostObject.class);

            if (problem.getStatusCode().is2xxSuccessful()) {
                return problem.getBody();
            } else if (problem.getStatusCode().is4xxClientError()) {
                throw new RuntimeException("Token expirado");
            } else {
                throw new RuntimeException("Ocorreu um erro no envio do problema");
            }

        } else {
            throw new RuntimeException("Devem ser informados no mínimo 2 pontos para cálculo de rota");
        }
    }

    public PostObject getProblemById(String token, String id) {
        if (!id.isEmpty() && !token.isEmpty()) {
            HttpHeaders header = new HttpHeaders();
            header.setBearerAuth(token);

            RequestEntity<PostObject> entity = new RequestEntity<>(header, HttpMethod.GET, URI.create(URL_PROBLEM.concat("/").concat(id)));
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new ErrorHandlerController());

            ResponseEntity<PostObject> getProblem = restTemplate.exchange(entity, PostObject.class);

            if (getProblem.getStatusCode().is2xxSuccessful()) {
                return getProblem.getBody();
            }
        }
        throw new RuntimeException("O id e/ou token não pode estar em branco");
    }
}
