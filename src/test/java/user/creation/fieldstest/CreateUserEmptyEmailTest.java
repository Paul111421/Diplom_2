package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class CreateUserEmptyEmailTest {

    private UserCard emptyEmailUser;

    @Before
    public void initializeUser(){
        emptyEmailUser = UserTestValues.userNotValidEmptyEmail;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустой почтой")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле email")
    public void createUserEmptyEmailTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(emptyEmailUser);
        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);
    }
}
