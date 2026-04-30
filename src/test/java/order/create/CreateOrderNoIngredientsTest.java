package order.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderApi;
import order.OrderCard;
import order.OrderTestValues;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderNoIngredientsTest {

    private OrderCard invalidOrderNoIngredients;

    @Before
    public void initializeOrder(){
        invalidOrderNoIngredients = OrderTestValues.orderNotValidEmptyList;
    }

    @Test
    @DisplayName("Проверка невозможности создания заказа с пустым списком")
    @Description("Проверить невозможность создания нового заказа при передаче пустого списка")
    public void createOrderWithAuthTest(){
        Response responseCreateNewOrder = OrderApi.createNewOrder(invalidOrderNoIngredients);
        OrderApi.createNewOrder400(responseCreateNewOrder);
    }
}
