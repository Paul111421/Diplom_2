package order.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderApi;
import order.OrderCard;
import order.OrderBaseTest;
import order.OrderTestValues;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class CreateOrderWithAuthTest extends OrderBaseTest {

    private UserCard validUser;
    private OrderCard validOrder;

    @Before
    public void initializeUserAndOrder(){
        validUser = UserTestValues.userValid;
        validOrder = OrderTestValues.orderValid;
    }

    @Test
    @DisplayName("Проверка создания заказа авторизованным пользователем")
    @Description("Проверить возможность создания нового заказа авторизованным пользователем")
    public void createOrderWithAuthTest(){

        Response responseCreateNewOrder = UserApi.createUniqueUser(validUser);
        UserApi.createUniqueUser200(responseCreateNewOrder);
        String bearerToken = UserApi.extractBearerToken(responseCreateNewOrder);

        OrderApi.createNewOrder200(OrderApi.createNewOrderWithAuth(bearerToken, validOrder));

        UserApi.deleteUniqueUserByToken(responseCreateNewOrder);
    }
}
