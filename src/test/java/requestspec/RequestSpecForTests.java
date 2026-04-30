package requestspec;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecForTests {
    public static RequestSpecification requestSpecForTestsWithHeader = given()
            .baseUri("https://stellarburgers.education-services.ru")
            .header("Content-Type","application/json");

    public static RequestSpecification requestSpecForTestsUriOnly = given()
            .baseUri("https://stellarburgers.education-services.ru");
}
