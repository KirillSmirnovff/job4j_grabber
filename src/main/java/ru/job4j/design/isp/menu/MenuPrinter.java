package ru.job4j.design.isp.menu;

public interface MenuPrinter {

    String MENU_INDENT = "----";

    void print(Menu menu);

}