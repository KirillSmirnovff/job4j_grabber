package ru.job4j.design.carparking;

import java.util.List;

public interface ParkingPlace {

    void parking(Car car, int size);

    int getFreeCells();

    List<Car> getAll();
}
