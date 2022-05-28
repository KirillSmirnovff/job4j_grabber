package ru.job4j.design.isp;

public class Piano implements MusicalInstrument {

    @Override
    public void sound() {
        System.out.println("playing some sounds");
    }

    @Override
    public void stringTuning() {
        System.out.println("Piano doesn't have strings!");
    }
}
