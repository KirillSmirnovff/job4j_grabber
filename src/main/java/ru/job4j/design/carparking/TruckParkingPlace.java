package ru.job4j.design.carparking;

import java.util.ArrayList;
import java.util.List;

public class TruckParkingPlace implements ParkingPlace {

    private int freeCells;
    private final List<Car> parkingCars = new ArrayList<>();

    public TruckParkingPlace(int freeCells) {
        this.freeCells = freeCells;
    }

    public List<Car> getParkingCars() {
        return parkingCars;
    }

    @Override
    public int getFreeCells() {
        return freeCells;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public void parking(Car car, int size) {

    }
}
