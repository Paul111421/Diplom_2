package order.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderApi;
import order.OrderBaseTest;
import order.OrderCard;
import order.OrderTestValues;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithIngredientsTest extends OrderBaseTest {

    private OrderCard validOrder;

    @Before
    public void initializeOrder(){
        validOrder = OrderTestValues.orderValid;
    }

    @Test
    @DisplayName("Проверка создания заказа правильным списком")
    @Description("Проверить возможность создания нового заказа при передаче правильно оформленного заказа")
    public void createOrderWithAuthTest(){

        Response responseCreateNewOrder = OrderApi.createNewOrder(validOrder);
        OrderApi.createNewOrder200(responseCreateNewOrder);

    }
}

