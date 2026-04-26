package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import user.UserApi;
import user.UserCard;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class OrderApi {

    @Step("Создать новый заказ (общий метод для других)")
    public static Response createNewOrder(OrderCard orderCard){
        return given()
                .header("Content-Type","application/json")
                .and()
                .body(orderCard)
                .when()
                .post(OrderEndpoints.orderCreate);
    }

    @Step("Создать новый заказ (с авторизацией пользователя)")
    public static Response createNewOrderWithAuth(String bearerToken, OrderCard orderCard){

        return given()
                .header("Content-Type","application/json")
                .auth().oauth2(bearerToken)
                .and()
                .body(orderCard)
                .when()
                .post(OrderEndpoints.orderCreate);

    }


    @Step("Успешное создание заказа (200 ОК)")
    public static void createNewOrder200(Response responseCreateNewOrder){
        responseCreateNewOrder.then().statusCode(SC_OK);
    }
    @Step("Ошибка создания заказа - не переданы ингредиенты (400 Bad Request)")
    public static void createNewOrder400(Response responseCreateNewOrder){
        responseCreateNewOrder.then().statusCode(SC_BAD_REQUEST);
    }

    @Step("Ошибка создания заказа - неправильный хэш ингредиента (500 Internal Server Error)")
    public static void createNewOrder500(Response responseCreateNewOrder){
        responseCreateNewOrder.then().statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
