package ru.job4j.design.food;

import java.util.List;

public interface Store {

    Boolean put(Food food, double expiredPercentage);

    List<Food> getByName(String name);

}
