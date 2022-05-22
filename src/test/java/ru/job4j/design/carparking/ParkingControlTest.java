package ru.job4j.design.carparking;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class ParkingControlTest {

    @Test
    public void whenCarSize1AndCellFree() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car car = new PassengerCar("Lada Kalina");
        control.distribute(car);
        List<Car> expected = List.of(car);
        assertThat(carParking.getAll(), is(expected));
    }

    @Test
    public void whenCarSize1AndCellNotFree() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car carOne = new PassengerCar("Lada Kalina");
        Car carTwo = new PassengerCar("Opel Astra");
        control.distribute(carOne);
        assertFalse(control.distribute(carTwo));
     }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongCarType() {
        new Truck("Lada Kalina", 1);
    }

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

    @Test
    public void whenCarSize2AndTruckCellFullAndPassengerCellFree() {
        ParkingPlace carParking = new CarParkingPlace(2);
        ParkingPlace truckParking = new TruckParkingPlace(1);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car carOne = new Truck("Zil", 2);
        Car carTwo = new Truck("Mercedes", 2);
        control.distribute(carOne);
        control.distribute(carTwo);
        List<Car> expected = List.of(carTwo);
        assertThat(carParking.getAll(), is(expected));
    }

    @Test
    public void whenCarSize2AndAllCellsFull() {
        ParkingPlace carParking = new CarParkingPlace(1);
        ParkingPlace truckParking = new TruckParkingPlace(0);
        ParkingControl control = new ParkingControl(carParking, truckParking);
        Car car = new Truck("Zil", 2);
        assertFalse(control.distribute(car));
    }
}