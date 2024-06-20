package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClient extends Client {

    private static final String COURIER_PATH = "/auth/register";
    private static final String LOGIN_PATH = "/auth/login/";
    private static final String GET_PATH = "/auth/user/";

    private static final String GET_ORDERCLIENT = "/orders";



    @Step("create user")
    public ValidatableResponse createUser (User user) {
        return spec()
                .body(user)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }
    @Step("login user")
    public ValidatableResponse loginUser(UserLoginCredentials creds) {
        return spec()
                .body(creds)
                .post(LOGIN_PATH)
                .then().log().all();
    }
    @Step("Get user data")
    public ValidatableResponse getUser(String token) {
        return spec()
                .header("authorization", token)
                .get(GET_PATH)
                .then().log().all();
    }

    @Step("Change user data")
    public ValidatableResponse changeUser(UserCredentials userCredentials, String token) {
        return spec()
                .header("authorization", token)
                .body(userCredentials)
                .patch(GET_PATH)
                .then().log().all();
    }

    @Step("Change user without token")
    public ValidatableResponse changeUserWithoutToken(UserCredentials userCredentials) {
        return spec()
                .body(userCredentials)
                .patch(GET_PATH)
                .then().log().all();
    }
    @Step("create user")
    public ValidatableResponse deleteUser (UserCredentials userCredentials, String token) {
        return spec()
                .header("authorization", token)
                .body(userCredentials)
                .when()
                .delete(GET_PATH)
                .then().log().all();
    }
    @Step("Get user order list")
    public ValidatableResponse getUserOrder(String token) {
        return spec()
                .header("authorization", token)
                .get(GET_ORDERCLIENT)
                .then().log().all();
    }
    @Step("Get user order list failed without login")
    public ValidatableResponse getUserOrderFailed() {
        return spec()
                .get(GET_ORDERCLIENT)
                .then().log().all();
    }


}
