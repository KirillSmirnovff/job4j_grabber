package ru.job4j.design.carparking;

import static ru.job4j.design.carparking.Constants.*;

public class ParkingControl {

    private final ParkingPlace carParking;
    private final ParkingPlace truckParking;

    public ParkingControl(ParkingPlace carParking, ParkingPlace truckParking) {
        this.carParking = carParking;
        this.truckParking = truckParking;
    }

    public boolean distribute(Car car) {
        int size = car.getSize();
        boolean result = size == PASSENGER_CAR_SIZE;
        if (result) {
            result = carParking.parking(car);
        } else {
            result = truckParking.parking(car);
            if (!result) {
                result = carParking.parking(car);
            }
        }
        return result;
    }
}
