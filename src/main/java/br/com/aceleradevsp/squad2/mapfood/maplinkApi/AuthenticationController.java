package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.Authentication;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.URLMaplink.URL_LOGIN;
import static java.util.Collections.singletonList;

@Component
public class AuthenticationController {


    @Nullable
    public Authentication login() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.put("client_id", singletonList("MuGg5EgxgGMUDwzW1pyD4jjnFH3DyAF2"));
        formData.put("client_secret", singletonList("95j9HzMxQQlT0T3h"));

        HttpEntity<MultiValueMap> entity = new HttpEntity<>(formData, headers);

        ResponseEntity<Authentication> authentication = restTemplate.postForEntity(URI.create(URL_LOGIN), entity, Authentication.class);

        if (authentication.getStatusCode().is2xxSuccessful()) {
            return authentication.getBody();
        }

        return null;
    }
}
