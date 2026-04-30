package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

//Инициализация юзера в Before (без бейзтеста так как на каждый тест почти что различные юзеры)
//Суть теста в @Test на проверку получаемой ошибки
public class CreateUserEmptyNameTest {

    private UserCard emptyNameUser;

    @Before
    @Step("Инициализация данных пользователя")
    public void initializeUser(){
        emptyNameUser = UserTestValues.userNotValidEmptyName;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустым именем")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле name")
    public void createUserEmptyNameTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(emptyNameUser);
        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);

    }
}
