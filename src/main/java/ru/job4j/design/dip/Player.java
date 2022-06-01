package ru.job4j.design.dip;

import java.util.Objects;

public class Player {

    private String login;

    public Player(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(login, player.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
