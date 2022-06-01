package ru.job4j.design.dip;

import java.util.HashMap;

public class GamingPlace {

    private HashMap<Player, Integer> gamesPlayed = new HashMap<>();

    public void play(Player playerOne, Player playerTwo, Chess chess) {
        if (playerOne.equals(playerTwo)) {
            System.out.println("Cant play with yourself");
            throw new IllegalArgumentException();
        }
        System.out.println("Some playing logic etc.");
    }
}
