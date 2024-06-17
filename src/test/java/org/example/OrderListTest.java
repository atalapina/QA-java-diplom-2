package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class OrderListTest {

    private final UserClient client = new UserClient();
    private final UserCreateChecks check = new UserCreateChecks();
    private User user;

    @Test
    public void getOrderListSuccess() {
        var user = User.random();
        client.createUser(user);

        var credentials = UserLoginCredentials.from(user);
        ValidatableResponse loginResponse = client.loginUser(credentials);

        String token = check.loginInSuccessfully(loginResponse);

        ValidatableResponse getResponse = client.getUserOrder(token);
        check.getOrderClientSuccessfully(getResponse);
    }
    @Test
    public void getOrderListFailed() {

        ValidatableResponse getResponse = client.getUserOrderFailed();
        check.getOrderClientFailed(getResponse);
    }
}
