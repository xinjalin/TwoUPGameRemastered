package com.example.new_coin;

public class Game {
    private String resultOfGame = "";
    public String gameOfTwoUp(Boolean coin1, Boolean coin2, String radioSelection, String playerName){
        if (radioSelection.equals("Heads Heads")) {
            if (coin1 && coin2) {
                resultOfGame = "Victory HH";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Heads : " + resultOfGame);
            }
            else if (!coin1 && !coin2) {
                resultOfGame = "You Lose HH";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Heads : " + resultOfGame);
            }
            else {
                resultOfGame = "Flip Again";
            }
        } else if (radioSelection.equals("Tails Tails")) {
            if (!coin1 && !coin2) {
                resultOfGame = "Victory TT";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Tails : " + resultOfGame);
            }
            else if (coin1 && coin2) {
                resultOfGame = "You Lose TT";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Tails : " + resultOfGame);
            }
            else {
                resultOfGame = "Flip Again";
            }
        }
        return resultOfGame;
    }
}
