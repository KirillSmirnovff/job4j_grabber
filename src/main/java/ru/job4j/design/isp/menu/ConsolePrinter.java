package ru.job4j.design.isp.menu;

public class ConsolePrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            String name = menuItemInfo.getName();
            int spaceSize = (int) number.chars().
                    filter(ch -> ch == '.').
                    count() - 1;
            System.out.println("----".repeat(spaceSize) + number + name);
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", null);
        menu.add(Menu.ROOT, "Покормить собаку", null);
        menu.add("Сходить в магазин", "Купить продукты", null);
        menu.add("Купить продукты", "Купить хлеб", null);
        menu.add("Купить продукты", "Купить молоко", null);
        MenuPrinter print = new ConsolePrinter();
        print.print(menu);
    }
}
