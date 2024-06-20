package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class OrderListTest {

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
    public void getOrderListSuccess() {
        randomUser();
        client.createUser(user);

        var credentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(credentials);

        String token = check.loginInSuccessfully(loginResponse);

        ValidatableResponse getResponse = client.getUserOrder(token);
        check.getOrderClientSuccessfully(getResponse);
    }

}
