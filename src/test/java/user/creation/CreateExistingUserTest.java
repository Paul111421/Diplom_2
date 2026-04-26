package user.creation;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserApi;
import user.UserBaseTest;
import user.UserCard;
import user.UserTestValues;

public class CreateExistingUserTest extends UserBaseTest {
    private UserCard validUser;

    @Before
    public void initializeUser(){
        validUser = UserTestValues.userValid;
    }

    @Test
    @DisplayName("Проверка невозможности создания одинаковых пользователей")
    @Description("Проверить невозможность создания двух пользователя в системе при вводе одних и тех же данных")
    public void createUniqueUserTest(){
        Response responseCreateUniqueUser = UserApi.createUniqueUser(validUser);
        Response responseCreateSameUniqueUserAgain = UserApi.createUniqueUser(validUser);
        UserApi.createUniqueUser403(responseCreateSameUniqueUserAgain);
        UserApi.deleteUniqueUser(responseCreateUniqueUser);
    }
}
