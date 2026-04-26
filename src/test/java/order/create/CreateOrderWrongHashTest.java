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

public class CreateOrderWrongHashTest extends OrderBaseTest {

    private OrderCard wrongHashOrder;

    @Before
    public void initializeOrder(){
        wrongHashOrder = OrderTestValues.orderNotValidWrongHash;
    }

    @Test
    @DisplayName("Проверка невозможности создания заказа при неправильном хэше ингредиента")
    @Description("Проверить невозможность создания нового заказа при передаче неправильного хэша какого-либо из ингредиентов")
    public void createOrderWithAuthTest(){

        Response responseCreateNewOrder = OrderApi.createNewOrder(wrongHashOrder);
        OrderApi.createNewOrder500(responseCreateNewOrder);

    }
}

