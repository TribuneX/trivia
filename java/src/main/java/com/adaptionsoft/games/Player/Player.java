package com.adaptionsoft.games.Player;

public class Player {

    private String playerName;
    private int place;

    public Player(String playerName) {
        this.playerName = playerName;
        this.place = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

    public void movePlayerForward(int move) {
        this.place = place + move;
    }

    public void movePlayerBackward(int move) {
        this.place = place - move;
    }
}
