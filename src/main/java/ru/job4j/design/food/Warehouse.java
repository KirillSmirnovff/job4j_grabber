package ru.job4j.design.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> storage = new ArrayList<>();

    @Override
    public Boolean put(Food food, double expiredPercentage) {
        boolean result = expiredPercentage < 25;
        if (result) {
            storage.add(food);
        }
        return result;
    }

    public List<Food> getByName(String name) {
        List<Food> result = new ArrayList<>();
        for (Food food : storage) {
            if (name.equals(food.getName())) {
                result.add(food);
            }
        }
        return result;
    }
}
