package ru.job4j.design.carparking;


import java.util.ArrayList;
import java.util.List;

public class CarParkingPlace implements ParkingPlace {

    private int freeCells;
    private final List<Car> parkingCars;

    public CarParkingPlace(int freeCells) {
        this.freeCells = freeCells;
        this.parkingCars = new ArrayList<>(freeCells);
    }

    @Override
    public List<Car> getAll() {
        return List.copyOf(parkingCars);
    }

    @Override
    public boolean parking(Car car) {
        int size = car.getSize();
        boolean result = freeCells >= size;
        if (result) {
            parkingCars.add(car);
            freeCells -= size;
            System.out.println(car + " distributed to passenger car parking");
        } else {
            System.out.println("Passenger car parking is full!");
        }
        return result;
    }
}
