package ru.job4j.design.spr;

public class Human implements Talk {
    @Override
    public void talk(String specie) {
        System.out.println("I'm talking");
    }
}
