package ru.job4j.design.food;

import java.util.Calendar;

public class Milk extends Food {

    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private double price;
    private int discount;

    public Milk(String name, Calendar expiryDate, Calendar createDate, double price) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;

    }

    public Milk() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Calendar getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(Calendar expiryDate) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public Calendar getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Calendar createDate) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.createDate = createDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Milk{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }

}
