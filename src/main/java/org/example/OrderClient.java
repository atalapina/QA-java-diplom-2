package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

public class OrderClient extends Client {

    private static final String GET_INGREDIENTS = "/ingredients";
    private static final String POST_ORDER = "/orders";


    @Step("Get ingredients data")
    public ValidatableResponse getIngredients() {
        return spec()
                .get(GET_INGREDIENTS)
                .then().log().all();
    }
    @Step("Create order without ingredients failed")
    public ValidatableResponse createOrderWithoutIngredients() {
        return spec()
                .post(POST_ORDER)
                .then().log().all();
    }
    @Step("Create order with ingredients failed")
    public ValidatableResponse createOrderWithIngredients(Order order) {
        return spec()
                .body(order)
                .post(POST_ORDER)
                .then().log().all();
    }
    @Step("Create order with unknown ingredients failed")
    public ValidatableResponse createOrderWithUnknownIngredients(Order order) {
        return spec()
                .body(order)
                .post(POST_ORDER)
                .then().log().all();
    }
}
