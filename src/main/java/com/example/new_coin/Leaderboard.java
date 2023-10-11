package com.example.new_coin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Leaderboard {

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Leaderboard");

        TableView<PlayerVictory> tableView = new TableView<>();

        ArrayList<PlayerRecord> historicalGameData = ReadFile.gameOutcomeData();
        ArrayList<PlayerVictory> pv = VictoryCounter.countPlayerVictories(historicalGameData);

        // sort the pv list and restrict the limit to 10 records
        pv.sort((o1, o2) -> o2.getVictoryCount() - o1.getVictoryCount());
        ArrayList<PlayerVictory> topTen = new ArrayList<>(pv.subList(0, Math.min(10, pv.size())));

        ObservableList<PlayerVictory> gameData = FXCollections.observableList(topTen);

        TableColumn<PlayerVictory, String> playerNameColumn = new TableColumn<>("Player Name");
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        playerNameColumn.setPrefWidth(200);

        TableColumn<PlayerVictory, Integer> victoryCountColumn = new TableColumn<>("Victory Count");
        victoryCountColumn.setCellValueFactory(new PropertyValueFactory<>("victoryCount"));
        victoryCountColumn.setPrefWidth(200);

        tableView.getColumns().addAll(playerNameColumn, victoryCountColumn);
        tableView.setItems(gameData);

        victoryCountColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableView.getSortOrder().add(victoryCountColumn);

        VBox layout = new VBox(tableView);
        Scene scene = new Scene(layout, 400, 400, true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
