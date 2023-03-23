package com.example.new_coin;

public class Game {
    private String resultOfGame = "";
    public String gameOfTwoUp(Boolean coin1, Boolean coin2, String radioSelection, String playerName){
        if (radioSelection.equals("HEADS_HEADS")) {
            if (coin1 && coin2) {
                resultOfGame = "Victory";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Heads : " + resultOfGame);
            }
            else if (!coin1 && !coin2) {
                resultOfGame = "You Lose";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Heads : " + resultOfGame);
            }
            else {
                resultOfGame = "Flip Again";
            }
        } else if (radioSelection.equals("TAILS_TAILS")) {
            if (!coin1 && !coin2) {
                resultOfGame = "Victory";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Tails : " + resultOfGame);
            }
            else if (coin1 && coin2) {
                resultOfGame = "You Lose";
                WriteFile.writeFileScores("Players Name " + playerName + " : Players Choice x2 Tails : " + resultOfGame);
            }
            else {
                resultOfGame = "Flip Again";
            }
        }
        return resultOfGame;
    }
}
