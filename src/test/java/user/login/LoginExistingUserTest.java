package user.login;

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

//Создание юзера в @Before (без бейзтеста так как на каждый тест почти что различные юзеры)
//Суть теста в @Test на проверку получаемой ошибки
//Удаление юзера в @After
public class LoginExistingUserTest {

    private UserCard validUser;
    private Response responseCreateUniqueUser;

    @Before
    @Step("Инициализация пользовательских данных")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }

    @Before
    @Step("Запись ответов на запросы по созданию профилей пользователей")
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя")
    @Description("Проверить возможность авторизации существующего в системе пользователя при вводе подходящих данных")
    public void loginExistingUserTest(){

        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(validUser);
        UserApi.loginUniqueUser200(responseLoginUniqueUser);

    }

    @After
    @Step("Удаление из системы созданного профиля пользователя")
    public void deleteUserForTest(){
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}
