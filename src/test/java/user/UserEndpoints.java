package user;

public class UserEndpoints {
    public static String userAuthRegisterEndpoint = "/api/auth/register";
    public static String userAuthLoginEndpoint = "/api/auth/login";
    public static String userAuthLogoutEndpoint = "/api/auth/logout";
    public static String userAuthFindOrDeleteEndpoint = "/api/auth/user"; //нет нужды? Оставлю на будущее, по хорошему надо удалять
                                                                          //но в описании API нет тела на удаление
}
