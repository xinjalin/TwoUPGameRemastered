package com.example.new_coin;

public class PlayerRecord {
    // Attribute Fields
    private String time;
    private String player;
    private String bet;
    private String result;

    @Override
    public String toString() {
        return "PlayerRecord{" +
                "time='" + time + '\'' +
                ", player='" + player + '\'' +
                ", bet='" + bet + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    // Constructor
    public PlayerRecord(String time, String player, String bet, String result) {
        this.time = time;
        this.player = player;
        this.bet = bet;
        this.result = result;
    }

    // Getters and Setters
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
