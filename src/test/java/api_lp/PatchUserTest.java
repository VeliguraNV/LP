package api_lp;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class PatchUserTest {
    private Endpoints endpoints;
    private Creds creds;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
        creds = new Creds();
    }

    @Test
    @DisplayName("Успешное изменение данных пользователя")
    public void editUser_success() {
        Creds creds = new Creds();
        creds.login_success(); //логинимся
        ValidatableResponse response = endpoints.editUserSuccess(creds.getToken()); //Вызываем метод изменения данных пользоавтеля
        response.assertThat().statusCode(200).body("status", is(true));
    }
@Test
@DisplayName("Неудачное изменение данных")
    public void editUser_fail() {
Creds creds = new Creds();
creds.login_success();
ValidatableResponse response = endpoints.editUserFail(creds.getToken()); // Вызываем метод изменения данных пользователя с некорректными данными
response.assertThat().statusCode(400).body("status", is(false));
    }
    @Test
    @DisplayName("Успешное изменение данных пользователя")
    public void editUserWithoutAuth_fail() {
         ValidatableResponse response = endpoints.editUserSuccess(creds.getToken()); //Вызываем метод изменения данных пользоавтеля
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message",contains(equalToIgnoringCase("Unauthorized")));
    }
}
