package org.example;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.User;
import org.example.UserClient;
import org.example.UserCreateChecks;
import org.junit.Assert;
import org.junit.Test;

public class CreateUserTest {
    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();

    private User user;

    private void randomUser() {
        user = User.random();
    }

    @Test
    public void createNewUser() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        check.createdSuccessfully(createResponse);
        deleteUser();
    }

    @Test
    public void createDoubleUser() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        check.createdSuccessfully(createResponse);

        ValidatableResponse createFailedResponse = client.createUser(user);
        check.createdFailed(createFailedResponse);

        deleteUser();
    }

    @Test
    public void createCourierWithoutEmail() {
        randomUser();
        user.setEmail("");
        ValidatableResponse createdFailedWithoutLogin = client.createUser(user);
        check.createdFailedWithoutLogin(createdFailedWithoutLogin);

    }

    @Test
    public void deleteUserSuccess() {
        user = User.random();

        client.createUser(user);
        ValidatableResponse loginResponse = client.loginUser(UserLoginCredentials.from(user));
        String token = check.loginInSuccessfully(loginResponse);

        ValidatableResponse getClientResponse = client.deleteUser(UserCredentials.from(user), token);
        check.checkDeleteUser(getClientResponse);

    }


    private void deleteUser() {
        ValidatableResponse loginResponse = client.loginUser(UserLoginCredentials.from(user));
        String token = check.loginInSuccessfully(loginResponse);
        client.deleteUser(UserCredentials.from(user), token);
    }

}