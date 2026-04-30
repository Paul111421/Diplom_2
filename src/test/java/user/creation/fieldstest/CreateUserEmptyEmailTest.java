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
public class CreateUserEmptyEmailTest {

    private UserCard emptyEmailUser;
    private Response responseCreateUniqueUser;

    @Before
    public void initializeUser(){
        emptyEmailUser = UserTestValues.userNotValidEmptyEmail;
    }

    @Before
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(emptyEmailUser);
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустой почтой")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле email")
    public void createUserEmptyEmailTest(){

        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);

    }
}
