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

//Знаю, что в задании не требуют, но позволю после душной ручной части порадоваться душе в автоматизации.
//Если работать тестером, то только автомазитором :)))

//По требованиям нужны только почта и пароль - от имени логин не зависит. Проверим-с!
public class LoginNoErrorByWrongNameTest extends UserBaseTest {

    private UserCard validUser;
    private UserCard notNameUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notNameUser = UserTestValues.userNotValidDifferentName;
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя при неправильном имени")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильного имени")
    public void loginNoErrorByWrongNameTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notNameUser);
        UserApi.loginUniqueUser200(responseLoginUniqueUser);
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}