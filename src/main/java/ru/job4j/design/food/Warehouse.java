package ru.job4j.design.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> storage = new ArrayList<>();

    @Override
    public boolean put(Food food, double expiredPercentage) {
        boolean result = expiredPercentage < 25;
        if (result) {
            storage.add(food);
        }
        return result;
    }

    @Override
    public List<Food> getStore() {
        return List.copyOf(storage);
    }
}
