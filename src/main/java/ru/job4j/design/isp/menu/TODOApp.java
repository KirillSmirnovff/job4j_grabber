package ru.job4j.design.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public void init(Menu menu, MenuPrinter printer) {
        boolean run = true;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            while (run) {
                System.out.println("Enter '1' to create a new TODO, '2' to show your TODO list or '3' to exit:");
                String in = input.readLine();
                if ("1".equals(in)) {
                    System.out.println("Enter your 'parent' task name or enter 'Root' to place it in root tasks:");
                    String parentName = input.readLine();
                    System.out.println("Enter your task name:");
                    String taskName = input.readLine();
                    try {
                        boolean result = menu.add(parentName, taskName, STUB_ACTION);
                        if (result) {
                            System.out.println("Task successfully added.");
                            continue;
                        }
                        System.out.printf("Cannot find parent task with %s name. Try again!%s", parentName, System.lineSeparator());
                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                    }
                } else if ("2".equals(in)) {
                    System.out.println("Your taskbar:");
                    printer.print(menu);
                } else if ("3".equals(in)) {
                    run = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Wrong entering data, try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsolePrinter();
        TODOApp app = new TODOApp();
        app.init(menu, printer);
    }
}
