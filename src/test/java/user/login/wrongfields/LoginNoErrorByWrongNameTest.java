package user.login.wrongfields;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

//Знаю, что в задании не требуют, но позволю после душной ручной части порадоваться душе в автоматизации.
//Если работать тестером, то только автомазитором :)))

//По требованиям нужны только почта и пароль - от имени логин не зависит. Проверим-с!
public class LoginNoErrorByWrongNameTest {

    private UserCard validUser;
    private UserCard notNameUser;
    private Response responseCreateUniqueUser;

    @Before
    @Step("Инициализация пользовательских данных и запись ответов на запросы по созданию профилей пользователей")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notNameUser = UserTestValues.userNotValidDifferentName;
        responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя при неправильном имени")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильного имени")
    public void loginNoErrorByWrongNameTest(){

        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notNameUser);
        UserApi.loginUniqueUser200(responseLoginUniqueUser);

    }

    @After
    @Step("Удаление из системы созданного профиля пользователя")
    public void deleteUserForTest(){
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}