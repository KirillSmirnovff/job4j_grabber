package ru.job4j.design.carparking;

import static ru.job4j.design.carparking.Constants.*;
import java.util.ArrayList;
import java.util.List;

public class TruckParkingPlace implements ParkingPlace {

    private int freeCells;
    private final List<Car> parkingCars;

    public TruckParkingPlace(int freeCells) {
        this.freeCells = freeCells;
        this.parkingCars = new ArrayList<>(freeCells);
    }

    @Override
    public List<Car> getAll() {
        return List.copyOf(parkingCars);
    }

    @Override
    public boolean parking(Car car) {
        boolean result = freeCells >= REGULAR_CAR_SIZE;
        if (result) {
            parkingCars.add(car);
            freeCells -= REGULAR_CAR_SIZE;
            System.out.println(car + " distributed to truck parking");
        } else {
            System.out.println("Truck parking is full, lets try passenger car parking");
        }
        return result;
    }
}
