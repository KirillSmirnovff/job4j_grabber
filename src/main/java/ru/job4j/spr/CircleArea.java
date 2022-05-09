package ru.job4j.spr;

import java.util.Map;

public class CircleArea implements Area {
    @Override
    public double getArea(Map<String, Double> args) {
        double result = 0;
        try {
            double radius = args.get("Radius");
            result = 3.14 * Math.pow(radius, 2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void print(double value) {
        System.out.println("Circle area = " + value);
    }

}
