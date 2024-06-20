package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class WithoutCreateUserTest {
    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();

    private User user;

    private void randomUser() {
        user = User.random();
    }

    @Test
    public void createCourierWithoutEmail() {
        randomUser();
        user.setEmail("");
        ValidatableResponse createdFailedWithoutLogin = client.createUser(user);
        check.createdFailedWithoutLogin(createdFailedWithoutLogin);

    }


    @Test
    public void loginUnknownUser() {

        UserLoginCredentials creds = new UserLoginCredentials("rtyiopf@yandex.ru", "P@ssw0rd123");

        ValidatableResponse loginUnknownUser = client.loginUser(creds);
        check.loginWithUnknownUser(loginUnknownUser);
    }

    @Test
    public void getOrderListFailed() {

        ValidatableResponse getResponse = client.getUserOrderFailed();
        check.getOrderClientFailed(getResponse);
    }



}
