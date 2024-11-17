package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserFieldsTests {
    private Endpoints endpoints;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
    }

    @Test
    @DisplayName("Успешный логин и получение полей пользователя ЛК")
    public void getUserFields_success() {
        Creds creds = new Creds();
        creds.login_success();
        User user = new User(creds.login, creds.getPassword());
        ValidatableResponse response = endpoints.getUserFields(creds.getToken());
        response.assertThat().statusCode(200).body("status", is(true)).body("result.login.value", equalToIgnoringCase(creds.login));
    }

    @Test
    @DisplayName("Получение полей без авторизации")
    public void getUserFieldsWithoutAuth_fail() {
        Creds creds = new Creds();
        ValidatableResponse response = endpoints.getUserFields(creds.getToken());
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }
}