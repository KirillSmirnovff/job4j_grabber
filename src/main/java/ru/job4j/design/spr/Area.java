package ru.job4j.design.spr;


import java.util.Map;

public interface Area {
    public double getArea(Map<String, Double> args);

    public void print(double value);
}
