package user.login.wrongfields;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserBaseTest;
import user.UserCard;
import user.UserTestValues;

public class LoginErrorByWrongEmailTest extends UserBaseTest {

    private UserCard validUser;
    private UserCard notEmailUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notEmailUser = UserTestValues.userNotValidDifferentEmail;
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя по неправильной почте")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильной почты")
    public void loginByWrongEmailTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notEmailUser);
        UserApi.loginUniqueUser401(responseLoginUniqueUser);
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}