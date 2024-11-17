package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserDictionariesTest {
      private Endpoints endpoints;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();

    }
       @Test
    @DisplayName("Успешное получение справочников для ЛК пользователя")
    public void getUserDictionaries_successTest() {
        Creds creds = new Creds();
        creds.login_success();
        ValidatableResponse response = endpoints.getUserDictionaries(creds.getToken());
        response.assertThat().statusCode(200).body("status", is(true));
    }
    @Test
    @DisplayName("Получение справочников для ЛК пользователя без авторизации")
    public void getUserDictionariesWithoutAuth_failTest() {
        ValidatableResponse response = endpoints.getUserDictionaries(null);
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message",contains(equalToIgnoringCase("Unauthorized")));
    }
        }
