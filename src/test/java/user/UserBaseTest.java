package user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public class UserBaseTest {

    @Before
    public void SetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
}
