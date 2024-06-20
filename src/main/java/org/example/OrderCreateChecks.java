package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class OrderCreateChecks {
    @Step("get ingredients list")
    public GetIngredientsResponse getIngredientsSuccess(ValidatableResponse getIngredients) {
        return getIngredients
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(GetIngredientsResponse.class);
    }
    @Step("check order without ingredients failed")
    public void changeFailedWithoutIngredients(ValidatableResponse createWithoutIngredients) {
        String message = createWithoutIngredients
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        assertEquals(message, "Ingredient ids must be provided");
    }
    @Step("check order with ingredients success")
    public void changeSuccessWithIngredients(ValidatableResponse createWithIngredients) {
        boolean success = createWithIngredients
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
        assertTrue(success);
    }
    @Step("check create order with unknown ingredients failed")
    public void createFailedWithUnknownIngredients(ValidatableResponse createOrderWithUnknownIngredients) {
        createOrderWithUnknownIngredients
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
}
