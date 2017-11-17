package com.adaptionsoft.games.PenaltyBox;

public interface PenaltyBoxInterface {

    void sendPlayerToTheBox(String name);
    boolean isPlayerInTheBox(String name);
    void removePlayerFromTheBox(String name);

}
