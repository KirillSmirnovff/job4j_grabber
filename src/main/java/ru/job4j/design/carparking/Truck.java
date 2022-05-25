package ru.job4j.design.carparking;

import static ru.job4j.design.carparking.Constants.*;

public class Truck implements Car {

    private String name;
    private int size;

    public Truck(String name, int size) {
        if (size <= PASSENGER_CAR_SIZE) {
            throw new IllegalArgumentException("This car needs to be a passenger car");
        }
        this.name = name;
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Truck{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
