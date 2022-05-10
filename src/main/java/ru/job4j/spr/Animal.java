package ru.job4j.spr;

public class Animal implements Talk {

    @Override
    public void talk(String specie) {
        if ("Dog".equals(specie)) {
            System.out.println("Bark");
        }
        if ("Cat".equals(specie)) {
            System.out.println("Meow");
        }
    }
}
