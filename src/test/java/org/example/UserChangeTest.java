package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class UserChangeTest {

    private final UserClient client = new UserClient();
    private final UserCreateChecks createChecks = new UserCreateChecks();
    private final UserChangeChecks changeChecks = new UserChangeChecks();


    @Test
    public void GetUserCheckStatus() {
        var user = User.random();
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
        var user = User.random();

        ValidatableResponse createResponse = client.createUser(user);
        createChecks.createdSuccessfully(createResponse);

        var credentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(credentials);

        String token = createChecks.loginInSuccessfully(loginResponse);

        var userCheck = User.random();
        var userCredentials = new UserCredentials(userCheck.getName(), userCheck.getEmail());
        ValidatableResponse getClientResponse = client.changeUser(userCredentials, token);

        var getUserResponse = changeChecks.ChangeUserSuccess(getClientResponse);

        Assert.assertEquals(userCredentials.getName(), getUserResponse.getUser().getName());
        Assert.assertEquals(userCredentials.getEmail().toLowerCase(), getUserResponse.getUser().getEmail().toLowerCase());
    }

    @Test
    public void ChangeUserFailed() {
        var user = User.random();

        ValidatableResponse createResponse = client.createUser(user);
        createChecks.createdSuccessfully(createResponse);

        var userCredentials = UserCredentials.from(user);

        ValidatableResponse getClientResponse = client.changeUserWithoutToken(userCredentials);

        changeChecks.changeFailedWithoutLogin(getClientResponse);
    }




}
