import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class UserFields {
    private Endpoints endpoints = new Endpoints();
private User user;
String token;


@Test
    @Description("Успешный вход")
    public void login_success() {
        Creds creds = new Creds();
        user = new User(creds.login, "Rd352sda462.");
        ValidatableResponse response = endpoints.login(user);
    token = response.extract().jsonPath().getString("refreshToken");
        response.assertThat().statusCode(200).body("status", is(true));

    }

    @Test
    public void getUserFields_success() {
        ValidatableResponse response = endpoints.getUserFields(token);
        response.assertThat().statusCode(200).body("status",is(true));
    }
}