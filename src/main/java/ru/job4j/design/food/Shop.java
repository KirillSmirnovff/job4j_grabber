package ru.job4j.design.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> storage = new ArrayList<>();

    @Override
    public boolean put(Food food, double expiredPercentage) {
        boolean result = false;
        if (expiredPercentage >= 25 && expiredPercentage < 75) {
            storage.add(food);
            result = true;
        } else if (expiredPercentage >= 75 && expiredPercentage < 100) {
            food.setDiscount(50);
            storage.add(food);
            result = true;
        }

        return result;
    }

    @Override
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
