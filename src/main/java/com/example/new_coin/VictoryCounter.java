package com.example.new_coin;

import java.util.ArrayList;

public class VictoryCounter {

    public static ArrayList<PlayerVictory> countPlayerVictories(ArrayList<PlayerRecord> records) {
        ArrayList<PlayerVictory> victoryList = new ArrayList<>();

        for (PlayerRecord record : records) {
            String playerName = record.getPlayer();
            String result = record.getResult();

            if (!result.contains("Lose")) {
                PlayerVictory existingRecord = findPlayerInList(victoryList, playerName);
                if (existingRecord == null) {
                    victoryList.add(new PlayerVictory(playerName, 1));
                } else {
                    existingRecord.incrementCount();
                }
            }
        }

        return victoryList;
    }

    private static PlayerVictory findPlayerInList(ArrayList<PlayerVictory> list, String playerName) {
        for (PlayerVictory pv : list) {
            if (pv.getPlayerName().equals(playerName)) {
                return pv;
            }
        }
        return null;
    }

}

