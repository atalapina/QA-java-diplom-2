package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateUserTest {
    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();

    private User user;

    private void randomUser() {
        user = User.random();
    }

    @After
    public void deleteUser() {
        ValidatableResponse loginResponse = client.loginUser(UserLoginCredentials.from(user));
        String token = check.loginInSuccessfully(loginResponse);
        client.deleteUser(UserCredentials.from(user), token);
    }

    @Test
    public void createNewUser() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        check.createdSuccessfully(createResponse);
        //deleteUser();
    }

    @Test
    public void createDoubleUser() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        check.createdSuccessfully(createResponse);

        ValidatableResponse createFailedResponse = client.createUser(user);
        check.createdFailed(createFailedResponse);

        //deleteUser();
    }


}