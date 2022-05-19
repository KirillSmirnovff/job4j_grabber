package ru.job4j.design.food;


import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControlQuality {

    private MemStore memStore;

    public ControlQuality(MemStore memStore) {
        this.memStore = memStore;
    }

    public void control(Food food) {
        Calendar now = GregorianCalendar.getInstance();
        double hoursExpired =  ChronoUnit.HOURS.between(now.toInstant(), food.getCreateDate().toInstant());
        double totalHours = ChronoUnit.HOURS.between(food.getCreateDate().toInstant(), food.getExpiryDate().toInstant());
        double expiredPercentage = hoursExpired / totalHours * 100;
        for (Store store : memStore.getAllStorages().values()) {
            boolean result = store.put(food, expiredPercentage);
            if (result) {
                break;
            }
        }
    }

}