package user.login.wrongfields;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class LoginErrorByWrongPasswordTest {

    private UserCard validUser;
    private UserCard notValidPasswordUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notValidPasswordUser = UserTestValues.userNotValidDifferentPassword;
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя по неправильному паролю")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильного пароля")
    public void loginByWrongPasswordTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notValidPasswordUser);
        UserApi.loginUniqueUser401(responseLoginUniqueUser);
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}