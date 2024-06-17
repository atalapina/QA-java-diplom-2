package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;

public class UserChangeChecks {

    @Step("get user return name success")
    public GetUserResponse GetInNameSuccess(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(GetUserResponse.class);
    }

    @Step("change name return success")
    public GetUserResponse ChangeUserSuccess(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(GetUserResponse.class);
    }

    @Step("check change user without login failed")
    public void changeFailedWithoutLogin(ValidatableResponse createWithoutLogin) {
        String message = createWithoutLogin
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");
        assertEquals(message, "You should be authorised");
    }
}

