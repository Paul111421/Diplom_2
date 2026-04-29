package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requestspec.RequestSpecForTests;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserApi extends RequestSpecForTests{

    @Step("Создать нового пользователя (общий метод для других)")
    public static Response createUniqueUser(UserCard userCard){
        return given()
                .spec(requestSpecForTestsWithHeader)
                .and()
                .body(userCard)
                .when()
                .post(UserEndpoints.USER_AUTH_REGISTER_ENDPOINT);
    }

    @Step("Авторизовать пользователя (общий для других метод)(необходимо сначала создать пользователя!!!")
    public static Response loginUniqueUserAfterCreatingUser(UserCard userCard){
        return given()
                .spec(requestSpecForTestsWithHeader)
                .and()
                .body(userCard)
                .when()
                .post(UserEndpoints.USER_AUTH_LOGIN_ENDPOINT);
    }

    @Step("Извлечь токен авторизации пользователя")
    public static String extractBearerToken(Response responseCreateUniqueUser){
        return responseCreateUniqueUser.then().extract().path("accessToken").toString().replace("Bearer ","");
    }

    @Step("Найти нового пользователя")
    public static Response findUniqueUserByToken(Response responseCreateUniqueUser){
        String bearerToken = extractBearerToken(responseCreateUniqueUser);

        return given()
                .auth().oauth2(bearerToken)
                .when()
                .get(UserEndpoints.USER_AUTH_FIND_OR_DELETE_ENDPOINT);
    }

    @Step("Удалить нового пользователя")
    public static void deleteUniqueUserByToken(Response responseCreateUniqueUser){
        String bearerToken = extractBearerToken(responseCreateUniqueUser);

        Response responseDeleteUniqueUser = given()
                .spec(requestSpecForTestsUriOnly)
                .auth().oauth2(bearerToken)
                .when()
                .delete(UserEndpoints.USER_AUTH_FIND_OR_DELETE_ENDPOINT);

        responseDeleteUniqueUser.then().statusCode(SC_ACCEPTED);
    }

    @Step("Проверить успешность создания нового пользователя (201 Created)")
    public static void createUniqueUser200(Response responseCreateUniqueUser){
        responseCreateUniqueUser.then().statusCode(SC_OK);
    }

    @Step("Проверить появление ошибки создания пользователя (403 Forbidden - \"User already exists\")")
    public static void createUniqueUser403UserAlreadyExists(Response responseCreateUniqueUser){
        responseCreateUniqueUser.then().statusCode(SC_FORBIDDEN);
        responseCreateUniqueUser.then()
                .assertThat()
                .body("success", equalTo(false),
                        "message",equalTo("User already exists"));
    }

    @Step("Проверить появление ошибки создания пользователя (403 Forbidden - \"User already exists\")")
    public static void createUniqueUser403EmptyField(Response responseCreateUniqueUser){
        responseCreateUniqueUser.then().statusCode(SC_FORBIDDEN);
        responseCreateUniqueUser.then()
                .assertThat()
                .body("success", equalTo(false),
                        "message",equalTo("Email, password and name are required fields"));
    }

    @Step("Проверить успешность авторизации существующего пользователя (200 ОК)")
    public static void loginUniqueUser200(Response responseLoginUniqueUser){
        responseLoginUniqueUser.then().statusCode(SC_OK);
    }

    @Step("Проверить появление ошибки при передаче неправильных данных для логина (401 Unauthorized)")
    public static void loginUniqueUser401(Response responseLoginUniqueUser){
        responseLoginUniqueUser.then().statusCode(SC_UNAUTHORIZED);
        responseLoginUniqueUser.then()
                .assertThat()
                .body("success", equalTo(false),
                        "message", equalTo("email or password are incorrect"));

    }
}
