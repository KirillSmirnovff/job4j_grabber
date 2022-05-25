package ru.job4j.design.food;

import java.util.Calendar;

public class Food {

    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private double price;
    private int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public Food() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        if (createDate.compareTo(expiryDate) > 0) {
            throw  new IllegalArgumentException("Create date cannot be equal or go after expiry date");
        }
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
