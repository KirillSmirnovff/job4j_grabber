package ru.job4j.design.isp;

public class AudioPlayer implements Reproducible {

    @Override
    public void playMusic() {
        System.out.println("playing music");
    }

    @Override
    public void playVideo() {
        System.out.println("Can't play video files!");
    }
}
