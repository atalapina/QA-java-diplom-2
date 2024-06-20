package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class UserChangeTest {

    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();
    private final UserCreateChecks createChecks = new UserCreateChecks();
    private final UserChangeChecks changeChecks = new UserChangeChecks();
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
    public void GetUserCheckStatus() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        createChecks.createdSuccessfully(createResponse);

        var credentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(credentials);

        String token = createChecks.loginInSuccessfully(loginResponse);
        Assert.assertFalse(token.isEmpty()||token.isBlank());

        ValidatableResponse getClientResponse = client.getUser(token);

        var getUserResponse = changeChecks.GetInNameSuccess(getClientResponse);
        Assert.assertEquals(user.getName(), getUserResponse.getUser().getName());
    }

    @Test
    public void ChangeUserSuccess() {
        randomUser();
        ValidatableResponse createResponse = client.createUser(user);
        createChecks.createdSuccessfully(createResponse);

        var loginCredentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(loginCredentials);

        String token = createChecks.loginInSuccessfully(loginResponse);

        randomUser();
        user.setPassword(loginCredentials.getPassword());

        var userCredentials = UserCredentials.from(user);
        ValidatableResponse getClientResponse = client.changeUser(userCredentials, token);

        var getUserResponse = changeChecks.ChangeUserSuccess(getClientResponse);

        Assert.assertEquals(userCredentials.getName(), getUserResponse.getUser().getName());
        Assert.assertEquals(userCredentials.getEmail().toLowerCase(), getUserResponse.getUser().getEmail().toLowerCase());
    }

    @Test
    public void ChangeUserFailed() {
        randomUser();

        ValidatableResponse createResponse = client.createUser(user);
        createChecks.createdSuccessfully(createResponse);

        var userCredentials = UserCredentials.from(user);

        ValidatableResponse getClientResponse = client.changeUserWithoutToken(userCredentials);

        changeChecks.changeFailedWithoutLogin(getClientResponse);
    }




}
