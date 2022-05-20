package ru.job4j.design.carparking;

public class ParkingControl {

    private final ParkingPlace carParking;
    private final ParkingPlace truckParking;

    public ParkingControl(ParkingPlace carParking, ParkingPlace truckParking) {
        this.carParking = carParking;
        this.truckParking = truckParking;
    }

    public void distribute(Car car) {

    }
}
