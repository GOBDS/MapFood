package br.com.aceleradevsp.squad2.mapfood.maplinkapi;

import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.Authentication;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.PointMap;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.domain.PostObject;
import br.com.aceleradevsp.squad2.mapfood.maplinkapi.exceptions.TokenExpiredException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostProblemControllerTest {

    @Autowired
    private PostProblemController controller;

    @Autowired
    private AuthenticationController authController;

    private List<PointMap> pointMaps;

    @Before
    public void setUp() {
        PointMap restaurant = new PointMap(-22.440460, -46.982140, "Restaurant 1");
        PointMap client = new PointMap(-22.431360, -46.955650, "Client X");

        pointMaps = new ArrayList<>();
        pointMaps.add(restaurant);
        pointMaps.add(client);
    }

    @Test(expected = TokenExpiredException.class)
    public void postProblemWithExpiredTokenShouldReturn401() {
        //Given
        String tokenExpired = "ynsHq0dRDKam6FgGQfgVtiiFXmbc";
        PostObject object = new PostObject();
        object.setPointMaps(pointMaps);

        //When
        PostObject problem = controller.sendProblem(object, tokenExpired);

        //Then
        fail("A exceção não ocorreu");
    }

    @Test
    public void postProblemShouldReturnId() {
        //Given
        Authentication login = authController.login();

        assertNotNull(login);
        assertNotNull(login.getAccessToken());

        PostObject object = new PostObject();
        object.setPointMaps(pointMaps);


        //When
        PostObject problem = controller.sendProblem(object, login.getAccessToken());

        //Then
        assertNotNull(problem.getId());
    }
}
