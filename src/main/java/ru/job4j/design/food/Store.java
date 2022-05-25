package ru.job4j.design.food;

import java.util.List;

public interface Store {

    boolean put(Food food, double expiredPercentage);

    List<Food> getStore();

}
