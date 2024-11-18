package api_lp;

import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class SupportTests {
    private Endpoints endpoints;
    private Creds creds;
    @Getter
    public String idAppeal;

    @BeforeEach
    public void setUp() {
        // Устанавливаем базовый объект Endpoints
        endpoints = new Endpoints();
        creds = new Creds();
    }

    @Test
    @DisplayName("Получение списка вопросов FAQ")
    public void getFaqTest() {
        Creds creds = new Creds();
        creds.login_success();
        ValidatableResponse response = endpoints.getFaq(creds.getToken());
        response.assertThat().statusCode(200)
                .body("status", is(true))
                .body("result", is(notNullValue()));
    }

    @Test
    @DisplayName("Получение списка вопросов FAQ без авторизации")
    public void getFaqWithoutAuthTest() {
        ValidatableResponse response = endpoints.getFaq(null);
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }

    @Test
    @DisplayName("Создание обращения и запрос списка обращений")
    public void createAppealTest() {
        Creds creds = new Creds();
        creds.login_success();
        ValidatableResponse response = endpoints.createAppeal(creds.getToken());
        response.assertThat().statusCode(200);
        idAppeal = response.extract().jsonPath().getString("result.id");
        ValidatableResponse response1 = endpoints.getSupportAppeals(creds.getToken());
        response1.assertThat().statusCode(200).body("result[0].id", equalTo(Integer.parseInt(idAppeal)));
    }

    @Test
    @DisplayName("Создание обращения без авторизации")
    public void createAppealWithoutAuthFailTest() {
        ValidatableResponse response = endpoints.createAppeal(creds.getToken());
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }

    @Test
    @DisplayName("Получение списка обращений без авторизации")
    public void getAppealsWithoutAuthFailTest() {
        ValidatableResponse response = endpoints.getSupportAppeals(creds.getToken());
        response.assertThat().statusCode(401).body("status", is(false)).and().body("errors.message", contains(equalToIgnoringCase("Unauthorized")));
    }

    @Test
    @DisplayName("Некорректный запрос создания обращения")
    public void createAppealFailTest() {
        Creds creds = new Creds();
        creds.login_success();
        ValidatableResponse response = endpoints.createAppealFail(creds.getToken());
        response.assertThat().statusCode(400).body("status", is(false));
    }
}

