package ru.job4j.design.food;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ControlQualityTest {

    @Test
    public void whenLessThen25() {
        MemStore memStore = new MemStore();
        memStore.getAllStorages().put("Warehouse", new Warehouse());
        memStore.getAllStorages().put("Shop", new Shop());
        memStore.getAllStorages().put("Trash", new Trash());
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 28),
                new GregorianCalendar(2022, Calendar.MAY, 18), 400);
        controlQuality.control(food);
        List<Food> expected = List.of(food);
        assertThat(memStore.getAllStorages().get("Warehouse").getByName("Cheddar"), is(expected));
    }

    @Test
    public void whenMoreThen25AndLessThen75() {
        MemStore memStore = new MemStore();
        memStore.getAllStorages().put("Warehouse", new Warehouse());
        memStore.getAllStorages().put("Shop", new Shop());
        memStore.getAllStorages().put("Trash", new Trash());
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 25),
                new GregorianCalendar(2022, Calendar.MAY, 15), 400);
        controlQuality.control(food);
        List<Food> expected = List.of(food);
        assertThat(memStore.getAllStorages().get("Shop").getByName("Cheddar"), is(expected));
    }

    @Test
    public void whenMoreThen75AndLessThen100() {
        MemStore memStore = new MemStore();
        memStore.getAllStorages().put("Warehouse", new Warehouse());
        memStore.getAllStorages().put("Shop", new Shop());
        memStore.getAllStorages().put("Trash", new Trash());
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 20),
                new GregorianCalendar(2022, Calendar.MAY, 12), 400);
        controlQuality.control(food);
        int expectedDiscount = 50;
        assertThat(memStore.getAllStorages().get("Shop").getByName("Cheddar").get(0).getDiscount(), is(expectedDiscount));
    }

    @Test
    public void whenMoreThen100() {
        MemStore memStore = new MemStore();
        memStore.getAllStorages().put("Warehouse", new Warehouse());
        memStore.getAllStorages().put("Shop", new Shop());
        memStore.getAllStorages().put("Trash", new Trash());
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 18),
                new GregorianCalendar(2022, Calendar.MAY, 15), 400);
        controlQuality.control(food);
        List<Food> expected = List.of(food);
        assertThat(memStore.getAllStorages().get("Trash").getByName("Cheddar"), is(expected));
    }
}