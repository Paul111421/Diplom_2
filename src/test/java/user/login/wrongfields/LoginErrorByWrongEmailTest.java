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

//Создание юзера в @Before (без бейзтеста так как на каждый тест почти что различные юзеры)
//Суть теста в @Test на проверку получаемой ошибки
//Удаление юзера в @After
public class LoginErrorByWrongEmailTest {

    private UserCard validUser;
    private UserCard notEmailUser;
    private Response responseCreateUniqueUser;

    @Before
    @Step("Инициализация пользовательских данных")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notEmailUser = UserTestValues.userNotValidDifferentEmail;
    }

    @Before
    @Step("Запись ответов на запросы по созданию профилей пользователей")
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя по неправильной почте")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильной почты")
    public void loginByWrongEmailTest(){

        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notEmailUser);
        UserApi.loginUniqueUser401(responseLoginUniqueUser);

    }

    @After
    @Step("Удаление из системы созданного профиля пользователя")
    public void deleteUserForTest(){
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}