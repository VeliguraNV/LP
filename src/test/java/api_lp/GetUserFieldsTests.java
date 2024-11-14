package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserFieldsTests {
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
@DisplayName("Успешный логин и получение полей пользователя ЛК")
    public void getUserFields_success() {
        login_success();
        Creds creds = new Creds();
        User user = new User(creds.login, creds.getPassword());
             ValidatableResponse response = endpoints.getUserFields(token);
          response.assertThat().statusCode(200).body("status",is(true)).body("result.login.value", equalToIgnoringCase(creds.login));
            }

            @Test
    @DisplayName("Получение полей без авторизации")
            public void getUserFieldsWithouthAuth_fail() {
                ValidatableResponse response = endpoints.getUserFields(token);
                response.assertThat().statusCode(401).body("status",is(false));
            }
}