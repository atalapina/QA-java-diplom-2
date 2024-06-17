package org.example;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class UserCreateChecks {

    @Step("check user created successfully")
    public void createdSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
        assertTrue(created);
    }
    @Step("check courier double user fail")
    public void createdFailed(ValidatableResponse createFailedResponse) {
        String message = createFailedResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");
        assertEquals(message, "User already exists");
    }

    @Step("check created without login")
    public void createdFailedWithoutLogin(ValidatableResponse createWithoutLogin) {
        String message = createWithoutLogin
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");
        assertEquals(message, "Email, password and name are required fields");
    }
    @Step("check return success")
    public String loginInSuccessfully(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("accessToken");

    }
    @Step("check login without password")
    public void loginWithUnknownUser(ValidatableResponse loginUnknownCourier) {
        String message = loginUnknownCourier
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");
        assertEquals(message, "email or password are incorrect");
    }
    @Step("delete user success")
    public void checkDeleteUser(ValidatableResponse deleteUser) {
        boolean delete = deleteUser
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .extract()
                .path("success");
        assertTrue(delete);
    }
    @Step("check user order list successfully")
    public void getOrderClientSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
        assertTrue(created);
    }
    @Step("check user order list failed")
    public void getOrderClientFailed(ValidatableResponse createResponse) {
        String message = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");
        assertEquals(message, "You should be authorised");
    }


}
