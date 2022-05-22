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
    public List<Car> getAll() {
        return getParkingCars();
    }

    @Override
    public boolean parking(Car car) {
        boolean result = freeCells >= 1;
        if (result) {
            parkingCars.add(car);
            freeCells -= 1;
            System.out.println(car + " distributed to truck parking");
        } else {
            System.out.println("Truck parking is full, lets try passenger car parking");
        }
        return result;
    }
}
