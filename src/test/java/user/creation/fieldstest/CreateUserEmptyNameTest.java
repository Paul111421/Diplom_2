package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

//Создание юзера в Before (без бейзтеста так как на каждый тест почти что различные юзеры)
//Суть теста в @Test на проверку получаемой ошибки
public class CreateUserEmptyNameTest {

    private UserCard emptyNameUser;
    private Response responseCreateUniqueUser;


    @Before
    public void initializeUser(){
        emptyNameUser = UserTestValues.userNotValidEmptyName;
    }

    @Before
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(emptyNameUser);
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустым именем")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле name")
    public void createUserEmptyNameTest(){

        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);

    }
}
