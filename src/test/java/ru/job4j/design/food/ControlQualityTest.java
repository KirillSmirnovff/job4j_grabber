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
        Calendar now = new GregorianCalendar(2022, Calendar.MAY, 19);
        List<Store> memStore = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 28),
                new GregorianCalendar(2022, Calendar.MAY, 18), 400);
        controlQuality.control(food, now);
        List<Food> expected = List.of(food);
        assertThat(memStore.get(0).getByName("Cheddar"), is(expected));
    }

    @Test
    public void whenMoreThen25AndLessThen75() {
        Calendar now = new GregorianCalendar(2022, Calendar.MAY, 19);
        List<Store> memStore = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 25),
                new GregorianCalendar(2022, Calendar.MAY, 15), 400);
        controlQuality.control(food, now);
        List<Food> expected = List.of(food);
        assertThat(memStore.get(1).getByName("Cheddar"), is(expected));
    }

    @Test
    public void whenMoreThen75AndLessThen100() {
        Calendar now = new GregorianCalendar(2022, Calendar.MAY, 19);
        List<Store> memStore = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 20),
                new GregorianCalendar(2022, Calendar.MAY, 12), 400);
        controlQuality.control(food, now);
        int expectedDiscount = 50;
        assertThat(memStore.get(1).getByName("Cheddar").get(0).getDiscount(), is(expectedDiscount));
    }

    @Test
    public void whenMoreThen100() {
        Calendar now = new GregorianCalendar(2022, Calendar.MAY, 19);
        List<Store> memStore = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        ControlQuality controlQuality = new ControlQuality(memStore);
        Food food = new Cheese("Cheddar", new GregorianCalendar(2022, Calendar.MAY, 18),
                new GregorianCalendar(2022, Calendar.MAY, 15), 400);
        controlQuality.control(food, now);
        List<Food> expected = List.of(food);
        assertThat(memStore.get(2).getByName("Cheddar"), is(expected));
    }
}