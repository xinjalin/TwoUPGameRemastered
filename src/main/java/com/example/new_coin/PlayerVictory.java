package com.example.new_coin;

public class PlayerVictory {
    private String playerName;
    private int victoryCount;

    public PlayerVictory(String playerName, int victoryCount) {
        this.playerName = playerName;
        this.victoryCount = victoryCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getVictoryCount() {
        return victoryCount;
    }

    public void incrementCount() {
        this.victoryCount++;
    }
}

