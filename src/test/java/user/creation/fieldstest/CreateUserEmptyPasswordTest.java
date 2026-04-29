package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class CreateUserEmptyPasswordTest {

    private UserCard emptyPasswordUser;

    @Before
    public void initializeUser(){
        emptyPasswordUser = UserTestValues.userNotValidEmptyPassword;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустым паролем")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле password")
    public void createUserEmptyPasswordTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(emptyPasswordUser);
        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);
    }

}
