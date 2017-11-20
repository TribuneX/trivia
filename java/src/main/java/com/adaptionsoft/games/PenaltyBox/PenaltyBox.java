package com.adaptionsoft.games.PenaltyBox;

import java.util.HashSet;
import java.util.Set;

public class PenaltyBox {

    private Set<String> box;

    public PenaltyBox(){
        box = new HashSet<>();
    }

    public void sendPlayerToTheBox(String name) {
        box.add(name);
    }

    public boolean isPlayerInTheBox(String name) {
        return box.contains(name);
    }

}
