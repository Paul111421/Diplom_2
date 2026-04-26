package user.creation;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserBaseTest;
import user.UserCard;
import user.UserTestValues;

public class CreateUniqueUserTest extends UserBaseTest {

    private UserCard validUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Проверить возможность создания нового пользователя в системе при вводе подходящих данных")
    public void createUniqueUserTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        UserApi.createUniqueUser200(responseCreateUniqueUser);
        UserApi.deleteUniqueUser(responseCreateUniqueUser);
    }


}
