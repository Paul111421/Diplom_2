package user.creation;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;


//Инициализация юзера в @Before (без бейзтеста так как на каждый тест почти что различные юзеры)
//Суть теста в @Test на проверку получаемой ошибки
//Удаление юзера в также в тесте - без инициализации юзеров в Before
//метод аннотации After не сможет принять переменную, потому в сам тест
public class CreateExistingUserErrorTest {

    private UserCard validUser;

    @Before
    @Step("Инициализация пользовательских данных")
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }


    @Test
    @DisplayName("Проверка невозможности создания одинаковых пользователей")
    @Description("Проверить невозможность создания двух пользователя в системе при вводе одних и тех же данных")
    public void createExistingUserErrorTest(){

        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseCreateSameUniqueUserAgain = UserApi.createUniqueUser(validUser);

        UserApi.createUniqueUser403UserAlreadyExists(responseCreateSameUniqueUserAgain);

        UserApi.deleteUniqueUserByToken(responseCreateUniqueUser);

    }

}
