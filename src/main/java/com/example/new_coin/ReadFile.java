package com.example.new_coin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<PlayerRecord> gameOutcomeData() {
        String filePath = "src\\Scores.txt";
        ArrayList<PlayerRecord> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            String[] splitData;

            while ((line = br.readLine()) != null) {
                splitData = line.split("\\|");
                PlayerRecord pr = new PlayerRecord(splitData[0], splitData[1], splitData[2], splitData[3]);
                data.add(pr);
            }

        } catch (IOException e){
        System.err.println("Error while reading from file: " + e.getMessage());
    }
        return data;
    }

}
