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

public class CreateUserNoEmailOrPasTest extends UserBaseTest {

    private UserCard noEmailOrPasUser;

    @Before
    public void initializeUser(){
        noEmailOrPasUser = UserTestValues.userNotValidNoEmailOrPas;
    }

    @Test
    @DisplayName("Проверка невозможности создания пользователя без полей Почта и Пароль со старта")
    @Description("Проверить невозможность создания нового пользователя в системе при отсутствующем поле Имя")
    public void createUserNoEmailOrPasTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(noEmailOrPasUser);
        UserApi.createUniqueUser403(responseCreateUniqueUser);
    }
}
