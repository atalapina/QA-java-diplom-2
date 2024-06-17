package org.example;

import io.restassured.response.ValidatableResponse;

import org.example.User;
import org.example.UserLoginCredentials;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class LoginUserTest {

    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();
    private User user;


    @Test
    public void loginUserCheckStatus() {
        var user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        check.createdSuccessfully(createResponse);

        var credentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(credentials);

        String token = check.loginInSuccessfully(loginResponse);
        Assert.assertFalse(token.isEmpty()||token.isBlank());
    }
    @Test
    public void loginUnknownUser() {

        UserLoginCredentials creds = new UserLoginCredentials("rtyiopf@yandex.ru", "P@ssw0rd123");

        ValidatableResponse loginUnknownUser = client.loginUser(creds);
        check.loginWithUnknownUser(loginUnknownUser);
    }



}
