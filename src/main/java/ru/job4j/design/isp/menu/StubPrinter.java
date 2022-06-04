package ru.job4j.design.isp.menu;

public class StubPrinter implements MenuPrinter {

    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            String name = menuItemInfo.getName();
            int spaceSize = (int) number.chars().
                    filter(ch -> ch == '.').
                    count() - 1;
            buffer.append(MENU_INDENT.repeat(spaceSize))
                    .append(number)
                    .append(name)
                    .append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
