package com.adaptionsoft.games.PenaltyBox;

import java.util.HashSet;
import java.util.Set;

public class PenaltyBox implements PenaltyBoxInterface {


    Set<String> box;

    public PenaltyBox(){
        box = new HashSet<>();
    }

    @Override
    public void sendPlayerToTheBox(String name) {
        box.add(name);
    }

    @Override
    public boolean isPlayerInTheBox(String name) {
        return box.contains(name);
    }

    @Override
    public void removePlayerFromTheBox(String name){
        box.remove(name);
    }
}
