package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserSettings {

    private String token;
    private Endpoints endpoints;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
    }
    public void login_success() {
        Creds creds = new Creds();
        User user = new User(creds.login, creds.password);
        ValidatableResponse response = endpoints.login(user);
        token = response.extract().jsonPath().getString("result.accessToken");
        response.assertThat().statusCode(200).body("status", is(true));
    }
    @Test
    @DisplayName("Успешное получение настроек пользователя")
    public void getUserSettingsWithAuth_test() {
        login_success();
        Creds creds = new Creds();
        User user = new User(creds.login, creds.password);
        ValidatableResponse response = endpoints.getUserSettings(token);
        token = response.extract().jsonPath().getString("result.accessToken");
        response.assertThat().statusCode(200).body("status", is(true));
    }
    @Test
    @DisplayName("Неудачное получение настроек пользователя без авторизации")
    public void getUserSettingsWithoutAuth_test() {
               ValidatableResponse response = endpoints.getUserSettings(token);
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message",contains(equalToIgnoringCase("Unauthorized")));
    }
}


