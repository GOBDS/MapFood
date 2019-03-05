package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController controller;

    @Test
    public void loginShouldReturn200() {
        //Given
        Authentication login = controller.login();

        //When
        if (login != null) {
            //Then
            assertNotNull(login.getAccessToken());
        } else {
            fail("O login não ocorreu com sucesso");
        }
    }
}
