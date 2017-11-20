package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    List<Player> players = new ArrayList<>();
    List<Integer> coins = new ArrayList<>();
    private int currentPlayer;

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        coins.add(new Integer(0));
    }

    public int getNumPlayers() {
        return players.size();
    }


    public String getCurrentPlayer() {
        return players.get(currentPlayer).getPlayerName();
    }

    public int getPlaceForCurrentPlayer() {
        return  players.get(currentPlayer).getPlace();
    }

    public void movePlayer(int move){
        players.get(currentPlayer).movePlayerForward(move);
        if (players.get(currentPlayer).getPlace() > 11) {
            players.get(currentPlayer).movePlayerBackward(12);
        }
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

    public int getCurrentCoins() {
        return coins.get(currentPlayer);
    }

    public void putNewCoinsForPlayer(){
        Integer newCoins = coins.get(currentPlayer);
        newCoins = newCoins + 1;
        coins.set(currentPlayer, newCoins);
    }

    public boolean didPlayerWin(){
       return !(getCurrentCoins() == 6);
    }
}
