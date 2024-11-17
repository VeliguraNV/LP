package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetNotificationsTest {
    private Endpoints endpoints;
private Creds creds;
    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
        creds = new Creds();
    }
    @Test
    @DisplayName("Получение списка уведомлений")
public void getNotificationsWithAuthTest() {
       creds.login_success();
        ValidatableResponse response = endpoints.getNotifications(creds.getToken());
        response.assertThat().statusCode(200).body("status", is(true));
    }
    @Test
    @DisplayName("Неудачное поучение списка уведомлений без авторизации")
    public void getNotificationsWithoutAuthTest() {
        ValidatableResponse response = endpoints.getNotifications(creds.getToken());
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }
}
