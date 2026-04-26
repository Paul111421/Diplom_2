package order;

import io.restassured.RestAssured;
import org.junit.Before;

public class OrderBaseTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }

}
