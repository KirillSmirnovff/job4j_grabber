package ru.job4j.design.isp;

public class GPhone implements Reproducible {

    @Override
    public void playMusic() {
        System.out.println("playing music");
    }

    @Override
    public void playVideo() {
        System.out.println("playing video");
    }
}
