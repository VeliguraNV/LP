import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class AuthTests {
    private Endpoints endpoints = new Endpoints();
    private User user;

    @Test
    @Description("Успешный вход")
    public void login_success() {
        Creds creds = new Creds();
        user = new User(creds.login, creds.password);
        ValidatableResponse response = endpoints.login(user);
        response.assertThat().statusCode(200).body("status", is(true));
    }
    @Test
    @Description("Неправильный пароль")
    public void login_fail() {
        Creds creds = new Creds();
        user = new User(creds.login, "123456789");
        ValidatableResponse response = endpoints.login(user);
        response.assertThat().statusCode(401).body("status", is(true));
    }
}
