package ru.job4j.design.food;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public void resort(Calendar now) {
        List<Food> resortList = new ArrayList<>();
        for (Store store : memStore) {
            resortList.addAll(store.getStore());
            store.clear();
        }
        for (Food food : resortList) {
            if (food.isDiscounted()) {
                food.setPrice(food.getPrice() * 100 / (100 - food.getDiscount()));
            }
            control(food, now);
        }
    }

    private double getPercentage(Food food, Calendar now) {
        double hoursExpired =  Math.abs(ChronoUnit.HOURS.between(now.toInstant(), food.getCreateDate().toInstant()));
        double totalHours = ChronoUnit.HOURS.between(food.getCreateDate().toInstant(), food.getExpiryDate().toInstant());
        return hoursExpired / totalHours * 100;
    }

}
