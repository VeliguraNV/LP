package api_lp;

import io.restassured.response.ValidatableResponse;



import static io.restassured.RestAssured.given;

public class Endpoints {
    public static final String BASE_URL = "https://stage3.lifepoint.club/api/v1/";


    public ValidatableResponse login(User user) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/auth")
                .then()
                .log().all();
    }

    public ValidatableResponse resetPassword(User user) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/auth/password/code")
                .then()
                .log().all();
    }

    public ValidatableResponse confirmPasswordReset() {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body("{\n \"login\" : \"Avtotest2\", \n\"password\": \"testPass_123\", \n\"verifCode\": \"731118\" \n}")
                .post("/auth/password")
                .then().log().all();
    }

    public ValidatableResponse getUserSettings(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/user/settings")
                .then()
                .log().all();
    }

    public ValidatableResponse getUserDictionaries(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/user/dictionaries")
                .then()
                .log().all();
    }

    public ValidatableResponse getUserFields(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("user/fields")
                .then()
                .log().all();
    }

    public ValidatableResponse editUserSuccess(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .body("{\n \"lastName\" : \"Тест\", \n\"name\": \"Автотест\", \n\"secondName\": \"Тестович\", \n\"phone\": \"" + System.currentTimeMillis() + "\" \n}")
                .patch("/user")
                .then().log().all();
    }

    public ValidatableResponse editUserFail(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "  \"birthday\": \"1994-02-4526T00:00:00+03:00\",\n" +
                        "  \"phone\": \"89991234567\",\n" +
                        "  \"gender\": \"F\",\n" +
                        "  \"number\": \"11111\",\n" +
                        "  \"sendingMail\": true\n" +
                        "}")
                .patch("/user")
                .then().log().all(); //необходима замена данных в телефоне
    }

    public ValidatableResponse getNotifications(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/notifications")
                .then().log().all();
    }
    public ValidatableResponse createAppeal(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "  \"topicId\": 33,\n" +
                        "  \"options\": {\n" +
                        "    \"hasPolicy\": true,\n" +
                        "    \"policy\": \"L0532/559/092837/3\",\n" +
                        "    \"clientFIO\": \"Ivanov Dobrynya Nikitich\",\n" +
                        "    \"clientBirthday\": \"2022-02-20T14:03:56+03:00\",\n" +
                        "    \"subCategory\": 35,\n" +
                        "    \"clientPhone\": \"9021111111\"\n" +
                        "  },\n" +
                        "  \"message\": \"Тестовое обращение\",\n" +
                        "  \"attachments\": [\n" +
                        "  ]\n" +
                        "}")
                .post("/support/appeal")
                .then().log().all();
    }
    public ValidatableResponse createAppealFail(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{{\n" +
                        "  \"topicId\": 1,\n" +
                        "  \"options\": {\n" +
                        "    \"hasPolicy\": true,\n" +
                        "    \"policy\": \"L0532/559/092837/3\",\n" +
                        "    \"clientFIO\": \"Ivanov Dobrynya Nikitich\",\n" +
                        "    \"clientBirthday\": \"2022-02-20T14:03:56+03:00\",\n" +
                        "    \"subCategory\": 35,\n" +
                        "    \"clientPhone\": \"9021111111\"\n" +
                        "  },\n" +
                        "  \"message\": \"Тестовое обращение\",\n" +
                        "  \"attachments\": [\n" +
                        "    1,\n" +
                        "    2,\n" +
                        "    3\n" +
                        "  ]\n" +
                        "}")
                .post("/support/appeal")
                .then().log().all();
    }
    public ValidatableResponse getFaq(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/support/faq")
                .then().log().all();
    }
    public ValidatableResponse getSupportAppeals(String token) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                 .formParam("limit", 5)
                .formParam("offset", 0)
                .header("Authorization", "Bearer " + token)
                .get("/support/appeals")
                .then().log().all();
    }

}
