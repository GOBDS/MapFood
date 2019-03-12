package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Authentication;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobControllerTest {

    @Autowired
    private JobController controller;

    @Autowired
    private AuthenticationController authController;


    @Test
    public void getJobByIdShouldReturn200() {
        //Given
        Authentication login = authController.login();

        assertNotNull(login);

        assertNotNull(login.getAccessToken());

        String problemId = "5c87e2a543804e0006e88a76";

        //When
        Job job = controller.getJobById(login.getAccessToken(), problemId);

        //Then
        assertNotNull(job);
    }
}
