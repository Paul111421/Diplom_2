package order.create;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderApi;
import order.OrderCard;
import order.OrderTestValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class CreateOrderWithAuthTest {

    private UserCard validUser;
    private OrderCard validOrder;
    private Response responseCreateNewOrder;

    @Before
    @Step("Инициализация данных и создание профиля пользователя")
    public void initializeUserAndOrder(){
        validUser = UserTestValues.userValid;
        validOrder = OrderTestValues.orderValid;

        responseCreateNewOrder = UserApi.createUniqueUser(validUser);
        UserApi.createUniqueUser200(responseCreateNewOrder);
    }

    @Test
    @DisplayName("Проверка создания заказа авторизованным пользователем")
    @Description("Проверить возможность создания нового заказа авторизованным пользователем")
    public void createOrderWithAuthTest(){

        String bearerToken = UserApi.extractBearerToken(responseCreateNewOrder);

        OrderApi.createNewOrder200(OrderApi.createNewOrderWithAuth(bearerToken, validOrder));

    }

    @After
    @Step("Удаление нового пользователя")
    public void deleteUserForTest(){

        UserApi.deleteUniqueUserByToken(responseCreateNewOrder);

    }
}
