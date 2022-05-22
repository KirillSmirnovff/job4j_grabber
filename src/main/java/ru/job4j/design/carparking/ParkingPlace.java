package ru.job4j.design.carparking;

import java.util.List;

public interface ParkingPlace {

    boolean parking(Car car);

    List<Car> getAll();
}
