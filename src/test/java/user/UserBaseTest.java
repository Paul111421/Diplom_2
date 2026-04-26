package user;

import io.restassured.RestAssured;
import org.junit.Before;

public class UserBaseTest {

    @Before
    public void SetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
}
