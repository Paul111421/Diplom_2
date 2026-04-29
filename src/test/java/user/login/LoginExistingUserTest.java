package user.login;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class LoginExistingUserTest {

    private UserCard validUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }

    @Test
    @DisplayName("Проверка авторизации пользователя")
    @Description("Проверить возможность авторизации существующего в системе пользователя при вводе подходящих данных")
    public void loginExistingUserTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(validUser);
        UserApi.loginUniqueUser200(responseLoginUniqueUser);
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}
