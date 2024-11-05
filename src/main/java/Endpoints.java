import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Endpoints {
    public static final String BASE_URL = "https://stage3.lifepoint.club/api/v1/";

    public ValidatableResponse getUserFields(String token){
      return   given()
                .log().all()
               .baseUri(BASE_URL)
              .auth().oauth2("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImlzcyI6InN0YWdlMy5saWZlcG9pbnQuY2x1YiJ9.eyJpc3MiOiJzdGFnZTMubGlmZXBvaW50LmNsdWIiLCJhdWQiOiJsaWZlcG9pbnQtcmVhY3QiLCJqdGkiOiJyZWFjdC42NzJhMDQwZjJjMGFkNy42ODEzNjUzOSIsInN1YiI6IlZlbGlndXJhbnYiLCJpYXQiOjE3MzA4MDY3OTkuMTgwNDA0OSwibmJmIjoxNzMwODA3Mzk5LjE4MDQwNDksImV4cCI6MTczMDgwNzM5OS4xODA0MDQ5LCJ1aWQiOiJyZWFjdC42NzJhMDQwZjJjMGFkNy42ODEzNjUzOSIsImxvZ2luIjoiVmVsaWd1cmFudiJ9.CInGuxDuMw34Orr-DQo5-Z7A17koPVZrVz1afpAKyng")
              .header("Content-type", "application/json")
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
}
