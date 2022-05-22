package ru.job4j.design.carparking;


import java.util.ArrayList;
import java.util.List;

public class CarParkingPlace implements ParkingPlace {

    private int freeCells;
    private final List<Car> parkingCars = new ArrayList<>();

    public CarParkingPlace(int freeCells) {
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
        int size = (int) Math.ceil(car.getSize());
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
