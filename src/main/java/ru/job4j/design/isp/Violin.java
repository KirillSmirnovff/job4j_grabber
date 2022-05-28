package ru.job4j.design.isp;

public class Violin implements MusicalInstrument {

    @Override
    public void sound() {
        System.out.println("playing some sounds");
    }

    @Override
    public void stringTuning() {
        System.out.println("tuning strings");
    }
}
