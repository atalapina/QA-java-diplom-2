package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateTest {
    private final OrderClient client = new OrderClient();
    private final OrderCreateChecks check = new OrderCreateChecks();



    @Test
    public void getIngredientsCheckStatus() {
        ValidatableResponse getResponse = client.getIngredients();
        check.getIngredientsSuccess(getResponse);
    }
    @Test
    public void createOrderWithoutIngredients() {
        ValidatableResponse createResponse = client.createOrderWithoutIngredients();
        check.changeFailedWithoutIngredients(createResponse);
    }
    @Test
    public void createOrderWithIngredients() {
        ValidatableResponse getResponse = client.getIngredients();
        var ingredientResponse = check.getIngredientsSuccess(getResponse);
        var order = Order.from(ingredientResponse.getData(), 3);
        ValidatableResponse createResponse = client.createOrderWithIngredients(order);
        check.changeSuccessWithIngredients(createResponse);
    }
    @Test
    public void createOrderWithUnknownIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("lklkdjf00ыыыlkdjfjk");
        ingredients.add("lklkdjf00ллдлддыlkdjfjk");
        Order order = Order.from(ingredients);
        ValidatableResponse createResponse = client.createOrderWithUnknownIngredients(order);
        check.createFailedWithUnknownIngredients(createResponse);
    }

}
