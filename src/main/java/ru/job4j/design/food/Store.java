package ru.job4j.design.food;

public interface Store {

    Boolean put(Food food, double expiredPercentage);

}
