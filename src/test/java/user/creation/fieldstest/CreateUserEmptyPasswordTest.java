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
public class CreateUserEmptyPasswordTest {

    private UserCard emptyPasswordUser;
    private Response responseCreateUniqueUser;

    @Before
    public void initializeUser(){
        emptyPasswordUser = UserTestValues.userNotValidEmptyPassword;
    }

    @Before
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(emptyPasswordUser);
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустым паролем")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле password")
    public void createUserEmptyPasswordTest(){

        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);

    }

}
