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
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            food.setDiscounted(true);
            storage.add(food);
            result = true;
        }

        return result;
    }

    @Override
    public List<Food> getStore() {
        return List.copyOf(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
