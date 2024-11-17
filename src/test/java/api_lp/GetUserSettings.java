package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserSettings {

    private Endpoints endpoints;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
    }

    @Test
    @DisplayName("Успешное получение настроек пользователя")
    public void getUserSettingsWithAuth_test() {
        Creds creds = new Creds();
        creds.login_success();
        ValidatableResponse response = endpoints.getUserSettings(creds.getToken());
          response.assertThat().statusCode(200).body("status", is(true));
    }

    @Test
    @DisplayName("Неудачное получение настроек пользователя без авторизации")
    public void getUserSettingsWithoutAuth_test() {
        Creds creds = new Creds();
        ValidatableResponse response = endpoints.getUserSettings(creds.getToken());
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }
}


