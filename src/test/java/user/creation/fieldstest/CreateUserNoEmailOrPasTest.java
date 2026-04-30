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
public class CreateUserNoEmailOrPasTest {

    private UserCard noEmailOrPasUser;
    private Response responseCreateUniqueUser;

    @Before
    public void initializeUser(){
        noEmailOrPasUser = UserTestValues.userNotValidNoEmailOrPas;
    }

    @Before
    public void createUserResponseForTest(){
        responseCreateUniqueUser = UserApi.createUniqueUser(noEmailOrPasUser);
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя без полей Почта и Пароль со старта")
    @Description("Проверить невозможность создания нового пользователя в системе при отсутствующем поле Имя")
    public void createUserNoEmailOrPasTest(){

        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);

    }
}
