import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class ResetPassTest {
    private Endpoints endpoints = new Endpoints();
    private User user;


@Test
@Description("Успешный сброс пароля с логином")
public void resetPassWithLogin_success() {
    Creds creds = new Creds();
    user = new User(creds.login); //инъекция зависимости creds
    ValidatableResponse response = endpoints.resetPassword(user);
    response.assertThat().statusCode(200).body("status", is(true));
}

@Test
@Description("Некорректный логин")
public void resetPass_fail(){
    user = new User("1");
    ValidatableResponse response = endpoints.resetPassword(user);
    response.assertThat().statusCode(400).body("status",is(false));
}

}
