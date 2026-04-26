package user;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class UserTestValues {
    static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

    static String email = fakeValuesService.bothify("????#@gmail.com");
    static String password = fakeValuesService.bothify("###");
    static String name = fakeValuesService.bothify("????");

    public static UserCard userValid = new UserCard(email, password, name);

    public static UserCard userNotValidDifferentEmail = new UserCard("super_email_777@gmail.ru", password, name);
    public static UserCard userNotValidDifferentPassword = new UserCard(email, "777", name);

    public static UserCard userNotValidEmptyEmail = new UserCard("", password, name);
    public static UserCard userNotValidEmptyPassword = new UserCard(email, "", name);
    public static UserCard userNotValidEmptyName = new UserCard(email, password, "");

    public static UserCard userNotValidNoName = new UserCard(email, password);
    public static UserCard userNotValidNoEmailOrPas = new UserCard(name);
}
