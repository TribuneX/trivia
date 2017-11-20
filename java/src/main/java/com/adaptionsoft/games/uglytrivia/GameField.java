package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.List;

public class GameField {

    List<Player> players = new LinkedList<>();

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public int getNumPlayers() {
        return players.size();
    }


    public String getCurrentPlayer(int currentPlayer) {
        return players.get(currentPlayer).getPlayerName();
    }

    public void setPlaceForCurrentPlayer(int currentPlayer, int place) {
        players.get(currentPlayer).setPlace(place);
    }

    public int getPlaceForCurrentPlayer(int currentPlayer) {
        return  players.get(currentPlayer).getPlace();
    }

    public void movePlayerForward(int currentPlayer, int move){
        players.get(currentPlayer).movePlayerForward(move);
    }

    public void movePlayerBackward(int currentPlayer, int move){
        players.get(currentPlayer).movePlayerBackward(move);
    }
}
