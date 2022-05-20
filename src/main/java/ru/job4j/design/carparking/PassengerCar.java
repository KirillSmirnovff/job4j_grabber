package ru.job4j.design.carparking;

public class PassengerCar implements Car {

    private String name;
    private double size;

    public PassengerCar(String name, double size) {
        if (size > 1) {
            throw new IllegalArgumentException("This car needs to be a Truck");
        }
        this.name = name;
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
     public double getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PassengerCar{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
