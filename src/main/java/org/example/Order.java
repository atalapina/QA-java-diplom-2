package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {

    private List<String> ingredients;
    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public Order() {
    }
    public static Order from(List<Ingredient> ingredients, int ingredientsCountNeed){
        Random random = new Random();
        List<String> ingredientsOrder = new ArrayList<String>();
        for(var i=0;i<ingredientsCountNeed;i++){
            ingredientsOrder.add(ingredients.get(random.nextInt(ingredients.size()-1)).get_id());
        }
        return new Order(ingredientsOrder);
    }
    public static Order from(List<String> ingredients){
        return new Order(new ArrayList< >(ingredients));
    }
}
