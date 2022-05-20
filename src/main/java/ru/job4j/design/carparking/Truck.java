package ru.job4j.design.carparking;

public class Truck implements Car {

    private String name;
    private double size;

    public Truck(String name, double size) {
        if (size <= 1) {
            throw new IllegalArgumentException("This car needs to be a passenger car");
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
    public String getName() {
        return name;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Truck{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
