package api_lp;

import io.restassured.response.ValidatableResponse;
import lombok.Getter;

import static org.hamcrest.Matchers.is;

public class Creds {
    @Getter
    String login = "Avtotest2";
     @Getter
     String password = "92%ax8T78}";
        @Getter
     String token;
    public ValidatableResponse login_success() {
        Endpoints endpoints = new Endpoints();
        Creds creds = new Creds();
        User user = new User(creds.login, creds.password);
        ValidatableResponse response = endpoints.login(user);
        token = response.extract().jsonPath().getString("result.accessToken");
        response.assertThat().statusCode(200).body("status", is(true));
        return response;
    }
}

