package ru.job4j.design.food;


import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    private List<Store> memStore;

    public ControlQuality(List<Store> memStore) {
        this.memStore = memStore;
    }

    public void control(Food food, Calendar now) {
        double expiredPercentage = getPercentage(food, now);
        for (Store store : memStore) {
            boolean result = store.put(food, expiredPercentage);
            if (result) {
                break;
            }
        }
    }

    private double getPercentage(Food food, Calendar now) {
        double hoursExpired =  Math.abs(ChronoUnit.HOURS.between(now.toInstant(), food.getCreateDate().toInstant()));
        double totalHours = ChronoUnit.HOURS.between(food.getCreateDate().toInstant(), food.getExpiryDate().toInstant());
        return hoursExpired / totalHours * 100;
    }

}
