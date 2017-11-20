package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.List;

public class GameField {

    List<Player> players = new LinkedList<>();
    private int currentPlayer;

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public int getNumPlayers() {
        return players.size();
    }


    public String getCurrentPlayer() {
        return players.get(currentPlayer).getPlayerName();
    }

    public void setPlaceForCurrentPlayer(int place) {
        players.get(currentPlayer).setPlace(place);
    }

    public int getPlaceForCurrentPlayer() {
        return  players.get(currentPlayer).getPlace();
    }

    public void movePlayerForward(int move){
        players.get(currentPlayer).movePlayerForward(move);
    }

    public void movePlayerBackward(int move){
        players.get(currentPlayer).movePlayerBackward(move);
    }

    public void nextPlayersTurn() {
        if (currentPlayer < players.size()-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }
    }

    public int getCurrentPlayerByID(){
        return currentPlayer;
    }
}
