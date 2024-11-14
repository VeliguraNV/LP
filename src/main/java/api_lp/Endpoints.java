package api_lp;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Endpoints {
    public static final String BASE_URL = "https://stage3.lifepoint.club/api/v1/";

    public ValidatableResponse getUserFields(String token){
      return   given()
                .log().all()
               .baseUri(BASE_URL)
               .header("Authorization", "Bearer " + token)
              .get("user/fields")
              .then()
              .log().all();
    }
    public ValidatableResponse login(User user){
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/auth")
                .then()
                .log().all();
    }
    public ValidatableResponse resetPassword(User user){
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/auth/password/code")
                .then()
                .log().all();
    }
    public ValidatableResponse getUserSettings(String token){
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/user/settings")
                .then()
                .log().all();
    }
}
