package ru.job4j.design.isp;

public class Dog implements Movable {
    @Override
    public void walk() {
        System.out.println("walking");
    }

    @Override
    public void fly() {
        throw new IllegalStateException("cannot fly");
    }
}
