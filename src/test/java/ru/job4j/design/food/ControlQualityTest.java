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
        Calendar now = Calendar.getInstance();
        Calendar createDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 1
        );
        Calendar expiryDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 10
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        Food cheese = new Cheese("Cheddar", expiryDate, createDate, 500, 30);
        controlQuality.control(cheese, now);
        List<Food> expected = List.of(cheese);
        assertThat(warehouse.getStore(), is(expected));
    }

    @Test
    public void whenMoreThen25AndLessThen75() {
        Calendar now = Calendar.getInstance();
        Calendar createDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 5
        );
        Calendar expiryDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 5
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        Food cheese = new Cheese("Cheddar", expiryDate, createDate, 500, 30);
        controlQuality.control(cheese, now);
        List<Food> expected = List.of(cheese);
        assertThat(shop.getStore(), is(expected));
    }

    @Test
    public void whenMoreThen75AndLessThen100() {
        Calendar now = Calendar.getInstance();
        Calendar createDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 10
        );
        Calendar expiryDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 2
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        Food cheese = new Cheese("Cheddar", expiryDate, createDate, 500, 30);
        controlQuality.control(cheese, now);
        List<Food> expected = List.of(cheese);
        assertThat(shop.getStore(), is(expected));
        assertThat(shop.getStore().get(0).getPrice(), is(350.0));
    }

    @Test
    public void whenMoreThen100() {
        Calendar now = Calendar.getInstance();
        Calendar createDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 10
        );
        Calendar expiryDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 1
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        Food cheese = new Cheese("Cheddar", expiryDate, createDate, 500, 30);
        controlQuality.control(cheese, now);
        List<Food> expected = List.of(cheese);
        assertThat(trash.getStore(), is(expected));
    }

    @Test
    public void whenControl() {
        Calendar now = Calendar.getInstance();
        Calendar createDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 1
        );
        Calendar expiryDate = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 10
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        Food cheese = new Cheese("Cheddar", (Calendar) expiryDate.clone(), (Calendar) createDate.clone(), 500, 30);
        createDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 5
        );
        expiryDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 5
        );
        Food milk = new Milk("Prostokvashino", (Calendar) expiryDate.clone(), (Calendar) createDate.clone(), 100, 40);
        createDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 10
        );
        expiryDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) + 2
        );
        Food discountCheese = new Cheese("Parmesan", (Calendar) expiryDate.clone(), (Calendar) createDate.clone(), 1000, 30);
        createDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 10
        );
        expiryDate.set(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH) - 2
        );
        Food spoiledMilk = new Milk("Vkusnoteevo", (Calendar) expiryDate.clone(), (Calendar) createDate.clone(), 100, 40);
        controlQuality.control(cheese, now);
        controlQuality.control(milk, now);
        controlQuality.control(spoiledMilk, now);
        controlQuality.control(discountCheese, now);
        List<Food> expectedWarehouse = List.of(cheese);
        List<Food> expectedShop = List.of(milk, discountCheese);
        List<Food> expectedTrash = List.of(spoiledMilk);
        assertThat(trash.getStore(), is(expectedTrash));
        assertThat(warehouse.getStore(), is(expectedWarehouse));
        assertThat(shop.getStore(), is(expectedShop));
        assertThat(shop.getStore().get(1).getPrice(), is(700.0));
    }
}