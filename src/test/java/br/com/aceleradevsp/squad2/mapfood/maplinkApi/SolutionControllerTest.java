package br.com.aceleradevsp.squad2.mapfood.maplinkApi;

import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.Authentication;
import br.com.aceleradevsp.squad2.mapfood.maplinkApi.domain.Solution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionControllerTest {

    @Autowired
    private AuthenticationController authController;

    @Autowired
    private SolutionController controller;

    @Test
    public void getSolutionByIdShouldReturn200() {
        //Given
        Authentication login = authController.login();

        assertNotNull(login);

        assertNotNull(login.getAccessToken());

        String problemId = "5c7e9fdec079cd0006a1b8df";

        //When
        Solution solution = controller.getSolutionById(login.getAccessToken(), problemId);

        //Then
        assertNotNull(solution);
    }
}
