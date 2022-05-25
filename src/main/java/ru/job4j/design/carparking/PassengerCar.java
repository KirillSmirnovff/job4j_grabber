package ru.job4j.design.carparking;

import static ru.job4j.design.carparking.Constants.*;

public class PassengerCar implements Car {

    private String name;
    private final int size = PASSENGER_CAR_SIZE;

    public PassengerCar(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
     public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PassengerCar{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
