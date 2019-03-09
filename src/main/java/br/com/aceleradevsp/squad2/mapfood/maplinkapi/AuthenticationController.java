package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Authentication;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationController {

    private AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    private Authentication login() {
        return service.login();
    }

    public String getTokenValid() {
        Authentication tokenValid = service.getTokenValid();
        if (tokenValid != null) {
            return tokenValid.getAccessToken();
        } else {
            Authentication login = login();
            if (!login.getAccessToken().isEmpty()) {
                return login.getAccessToken();
            } else {
                throw new InvalidDataException("Erro ao obter um novo token");
            }
        }
    }
}
