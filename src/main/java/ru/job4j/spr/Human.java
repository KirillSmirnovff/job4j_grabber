package ru.job4j.spr;

public class Human implements Talk {
    @Override
    public void talk(String specie) {
        System.out.println("I'm talking");
    }
}
