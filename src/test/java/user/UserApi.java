package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UserApi {

    @Step("Создать нового пользователя (общий метод для других)")
    public static Response createUniqueUser(UserCard userCard){
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(userCard)
                .when()
                .post(UserEndpoints.userAuthRegisterEndpoint);
    }

    @Step("Извлечь токен авторизации пользователя")
    public static String extractBearerToken(Response responseCreateUniqueUser){
        return responseCreateUniqueUser.then().extract().path("accessToken").toString().replace("Bearer ","");
    }

    @Step("Найти нового пользователя")
    public static Response findUniqueUser(Response responseCreateUniqueUser){
        String bearerToken = extractBearerToken(responseCreateUniqueUser);

        return given()
                .auth().oauth2(bearerToken)
                .when()
                .get(UserEndpoints.userAuthFindOrDeleteEndpoint);
    }

    @Step("Удалить нового пользователя")
    public static void deleteUniqueUser(Response responseCreateUniqueUser){
        String bearerToken = extractBearerToken(responseCreateUniqueUser);

        Response responseDeleteUniqueUser = given()
                .auth().oauth2(bearerToken)
                .when()
                .delete(UserEndpoints.userAuthFindOrDeleteEndpoint);

        responseDeleteUniqueUser.then().statusCode(SC_ACCEPTED);
    }

    @Step("Проверить успешность создания нового пользователя (201 Created)")
    public static void createUniqueUser200(Response responseCreateUniqueUser){
        responseCreateUniqueUser.then().statusCode(SC_OK);
    }

    @Step("Проверить появление ошибки создания пользователя (403 Forbidden)")
    public static void createUniqueUser403(Response responseCreateUniqueUser){
        responseCreateUniqueUser.then().statusCode(SC_FORBIDDEN);
    }
}
