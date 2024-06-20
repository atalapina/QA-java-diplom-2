package org.example;

import java.util.List;

public class OrdersList {
    private List<GetIngredientsResponse> orders;

    public List<GetIngredientsResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<GetIngredientsResponse> orders) {
        this.orders = orders;
    }
}
