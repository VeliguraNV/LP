package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class GetResetPassTest {
    private Endpoints endpoints = new Endpoints();
    private User user;


    @Test
    @DisplayName("Успешный сброс пароля с логином")
    public void resetPassWithLogin_success() {
        Creds creds = new Creds();
        user = new User(creds.login); //инъекция зависимости creds
        ValidatableResponse response = endpoints.resetPassword(user);
        response.assertThat().statusCode(200).body("status", is(true));
    }

    @Test
    @DisplayName("Некорректный логин при сбросе пароля")
    public void resetPass_fail() {
        user = new User("1");
        ValidatableResponse response = endpoints.resetPassword(user);
        response.assertThat().statusCode(400).body("status", is(false));
    }

    @Test
    @DisplayName("Неудачное подтверждение проверочного кода и сброс пароля")
    public void confirmPassTest() {
        ValidatableResponse response = endpoints.confirmPasswordReset();
        response.assertThat().statusCode(400).body("status", is(false));
        //не работает ручка?
    }


}
