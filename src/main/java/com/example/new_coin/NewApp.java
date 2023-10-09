package com.example.new_coin;

import javafx.application.Application;
import javafx.stage.Stage;

public class NewApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.display(primaryStage);
    }
}