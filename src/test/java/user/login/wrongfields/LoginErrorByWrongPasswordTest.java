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
public class LoginErrorByWrongPasswordTest {

    private UserCard validUser;
    private UserCard notValidPasswordUser;
    private Response responseCreateUniqueUser;

    @Before
    @Step("Инициализация пользовательских данных и запись ответов на запросы по созданию профилей пользователей")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
        notValidPasswordUser = UserTestValues.userNotValidDifferentPassword;
        responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
    }

    @Test
    @DisplayName("Проверка ошибки логина пользователя по неправильному паролю")
    @Description("Проверить ошибку авторизации существующего в системе пользователя при вводе неправильного пароля")
    public void loginByWrongPasswordTest(){

        Response responseLoginUniqueUser = UserApi.loginUniqueUserAfterCreatingUser(notValidPasswordUser);
        UserApi.loginUniqueUser401(responseLoginUniqueUser);

    }

    @After
    @Step("Удаление из системы созданного профиля пользователя")
    public void deleteUserForTest(){
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}