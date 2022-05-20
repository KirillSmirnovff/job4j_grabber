package ru.job4j.design.carparking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingControlTest {

    @Ignore
    @Test
    public void whenCarSize1AndCellFree() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car car = new PassengerCar("Lada Kalina", 1);
        control.distribute(car);
        List<Car> expected = List.of(car);
        assertThat(carParking.getAll(), is(expected));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenCarSize1AndCellNotFree() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car carOne = new PassengerCar("Lada Kalina", 0.5);
        Car carTwo = new PassengerCar("Opel Astra", 1);
        control.distribute(carOne);
        control.distribute(carTwo);
     }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenWrongCarType() {
        new PassengerCar("Lada Kalina", 2);
    }

    @Ignore
    @Test
    public void whenCarSize2AndTruckCellFree() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car car = new Truck("Zil", 2);
        control.distribute(car);
        List<Car> expected = List.of(car);
        assertThat(truckParking.getAll(), is(expected));
    }

    @Ignore
    @Test
    public void whenCarSize2AndTruckCellFullAndPassengerCellFree() {
        ParkingPlace carParking = new CarParkingPlace(3);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car carOne = new Truck("Zil", 2);
        Car carTwo = new Truck("Mercedes", 2);
        control.distribute(carOne);
        control.distribute(carTwo);
        List<Car> expected = List.of(carTwo);
        assertThat(carParking.getAll(), is(expected));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenCarSize2AndAllCellsFull() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(0);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car car = new Truck("Zil", 2);
        control.distribute(car);
    }
}