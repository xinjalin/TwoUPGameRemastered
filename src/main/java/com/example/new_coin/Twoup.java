package com.example.new_coin;

public class Twoup {
    private String resultOfGame = "";

    public String playGame(Boolean c1, Boolean c2, String currentBet, String playerName) {
        if (playerName.equals("")) {
            return "No Player";
        }

        switch (currentBet) {
            case "Heads Heads":
                if (c1 && c2) {
                    resultOfGame = "HH";
                    WriteFile.writeFileScores(
                            "|" + playerName + "|" + currentBet + "|" + resultOfGame
                    );
                } else if (!c1 && !c2) {
                    resultOfGame = "HH Lose";
                    WriteFile.writeFileScores(
                            "|" + playerName + "|" + currentBet + "|" + resultOfGame
                    );
                } else {
                    resultOfGame = "HH Flip Again";
                }
                break;

            case "Tails Tails":
                if (!c1 && !c2) {
                    resultOfGame = "TT";
                    WriteFile.writeFileScores(
                            "|" + playerName + "|" + currentBet + "|" + resultOfGame
                    );
                } else if (c1 && c2) {
                    resultOfGame = "TT Lose";
                    WriteFile.writeFileScores(
                            "|" + playerName + "|" + currentBet + "|" + resultOfGame
                    );
                } else {
                    resultOfGame = "TT Flip Again";
                }
                break;

        }

        return resultOfGame;
    }
}
