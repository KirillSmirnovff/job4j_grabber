package ru.job4j.design.isp;

public class Bird implements Movable {
    @Override
    public void walk() {
        System.out.println("walking");
    }

    @Override
    public void fly() {
        System.out.println("flying");
    }
}
