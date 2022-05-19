package ru.job4j.design.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> storage = new ArrayList<>();

    @Override
    public Boolean put(Food food, double expiredPercentage) {
        boolean result = expiredPercentage >= 100;
        if (result) {
            storage.add(food);
        }
        return result;
    }
}
