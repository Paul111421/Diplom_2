package user.creation.fieldstest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserCard;
import user.UserTestValues;

public class CreateUserNoNameTest {

    private UserCard noNameUser;

    @Before
    public void initializeUser(){
        noNameUser = UserTestValues.userNotValidNoName;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя без поля Имя со старта")
    @Description("Проверить невозможность создания нового пользователя в системе при отсутствующем поле Имя")
    public void createUserNoNameTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(noNameUser);
        UserApi.createUniqueUser403EmptyField(responseCreateUniqueUser);
    }
}
