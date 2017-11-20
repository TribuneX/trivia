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
}
