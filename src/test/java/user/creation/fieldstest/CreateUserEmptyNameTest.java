package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserBaseTest;
import user.UserCard;
import user.UserTestValues;

public class CreateUserEmptyNameTest extends UserBaseTest {

    private UserCard emptyNameUser;

    @Before
    public void initializeUser(){
        emptyNameUser = UserTestValues.userNotValidEmptyName;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя с пустым именем")
    @Description("Проверить невозможность создания нового пользователя в системе при пустом поле name")
    public void createUserEmptyNameTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(emptyNameUser);
        UserApi.createUniqueUser403(responseCreateUniqueUser);
    }
}
