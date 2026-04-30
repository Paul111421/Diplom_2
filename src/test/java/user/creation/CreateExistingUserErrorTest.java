package user.creation;

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
public class CreateExistingUserErrorTest {

    private UserCard validUser;
    private Response responseCreateUniqueUser;
    private Response responseCreateSameUniqueUserAgain;

    @Before
    @Step("Инициализация пользовательских данных")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }

    @Before
    @Step("Запись ответов на запросы по созданию профилей пользователей")
    public void createUserResponseForExistingUserTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        responseCreateSameUniqueUserAgain = UserApi.createUniqueUser(validUser);
    }

    @Test
    @DisplayName("Проверка невозможности создания одинаковых пользователей")
    @Description("Проверить невозможность создания двух пользователя в системе при вводе одних и тех же данных")
    public void createExistingUserErrorTest(){

        UserApi.createUniqueUser403UserAlreadyExists(responseCreateSameUniqueUserAgain);

    }

    @After
    @Step("Удаление из системы созданного профиля пользователя")
    public void deleteUserForTest(){
        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);
    }

}
