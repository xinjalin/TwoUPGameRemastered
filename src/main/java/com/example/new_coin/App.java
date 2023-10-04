package com.example.new_coin;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.animation.Timeline;

import java.util.ArrayList;

public class App extends Application {
    public String selectedRadioBtn = "";
    public Button playGame;
    public Button reset;

    public static void main(String[] args) {
        // start application
        launch(args);
    }

    public void start(Stage stage) {

        // coin textures
        Image coinHeadsTexture = new Image("coin_4.png");
        Image coinTailsTexture = new Image("coin_0.png");

        // cylinder material
        PhongMaterial c1material = new PhongMaterial();
        PhongMaterial c2material = new PhongMaterial();

        // create a cylinder with the dimensions of a coin
        Cylinder c1Cylinder = CoinAnimation.coinModel(c1material, coinHeadsTexture, 200, 200, 100);
        Cylinder c2Cylinder = CoinAnimation.coinModel(c2material, coinHeadsTexture, 500, 200, 100);

        // create an animation loop for the cylinder
        Timeline c1Timeline = CoinAnimation.createSpinningTimeline(
                c1Cylinder,
                c1material,
                coinHeadsTexture,
                coinTailsTexture
        );
        c1Timeline.play();

        Timeline c2Timeline = CoinAnimation.createSpinningTimeline(
                c2Cylinder,
                c2material,
                coinHeadsTexture,
                coinTailsTexture
        );
        c2Timeline.play();

        // ===== GUI Elements ===== //
        Label gameState = GUITemplates.customLabel("gameState", 300,50);

        Label playersNameLabel = GUITemplates.customLabel("Players Name", 0,400);
        TextField playersNameTextField = GUITemplates.customTextField(100,400);

        // select a bet radio button group
        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = GUITemplates.customRadioBtn("Heads Heads", 200, 350);
        RadioButton r2 = GUITemplates.customRadioBtn("Tails Tails", 400, 350);

        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);

        r1.setOnAction(e -> {
            selectedRadioBtn = r1.getText();
            playGame.setDisable(false);
        });
        r2.setOnAction(e -> {
            selectedRadioBtn = r2.getText();
            playGame.setDisable(false);
        });

        // button to play a game of twoup
        playGame = GUITemplates.customBtn("Play Game",300,400);
        playGame.setDisable(true);
        playGame.setOnAction(e -> {
            reset.setDisable(false);
            r1.setDisable(true);
            r2.setDisable(true);
            String currentBet = getCurrentBet();
            Coin c1 = new Coin();
            Coin c2 = new Coin();
            c1.flip();
            c2.flip();
            Image c1Texture = CoinAnimation.coinTexture(c1);
            Image c2Texture = CoinAnimation.coinTexture(c2);

            // add playersName input gui
            String gameResult = CoinAnimation.handleGame(c1, c2, currentBet, playersNameTextField.getText());

            if (gameResult.equals("HH Flip Again")||(gameResult.equals("TT Flip Again"))) {
                playGame.setText("Flip Again");
            } else {
                playGame.setDisable(true);
            }

            if (gameResult.equals("No Player")) {
                gameState.setText("Please Enter Players Name");
                tg.selectToggle(null);
                r1.setDisable(false);
                r2.setDisable(false);
                reset.setDisable(true);
            }

            switch (gameResult) {
                case "HH", "HH Lose", "TT", "TT Lose", "HH Flip Again", "TT Flip Again" -> {
                    CoinAnimation.handleCoinAnimationStop(
                            c1Timeline,
                            c2Timeline,
                            c1Cylinder,
                            c2Cylinder,
                            c1material,
                            c2material,
                            c1Texture,
                            c2Texture
                    );
                    gameState.setText(gameResult);
                }
            }
        });

        // resets the application to default state
        reset = GUITemplates.customBtn("reset",300,500);
        reset.setDisable(true);
        reset.setOnAction(e -> {
            c1Timeline.play();
            c2Timeline.play();
            tg.selectToggle(null);
            gameState.setText("");
            playGame.setText("Play Game");
            playGame.setDisable(true);
            reset.setDisable(true);
            r1.setDisable(false);
            r2.setDisable(false);
            playersNameTextField.setText("");
        });

        Button leaderboard = GUITemplates.customBtn("read file", 100, 100);
        leaderboard.setOnAction(e -> {
            ArrayList<PlayerRecord> historicalGameData = ReadFile.gameOutcomeData();

            for (PlayerRecord pr : historicalGameData) {
                System.out.println(pr.toString()); //debug
            }

        });

        // menu bar for user settings / Preferences
        MenuBar menuBar = new MenuBar();
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);
        menuBar.setMinHeight(30);

        Menu prefMenu = new Menu("Preferences");
        Menu fontSizeMenuItem = new Menu("Font Size");
        MenuItem fontSizeSmall = new MenuItem("Small");
        MenuItem fontSizeMedium = new MenuItem("Medium");
        MenuItem fontSizeLarge = new MenuItem("Large");

        prefMenu.getItems().addAll(fontSizeMenuItem);
        fontSizeMenuItem.getItems().addAll(fontSizeSmall, fontSizeMedium, fontSizeLarge);
        menuBar.getMenus().addAll(prefMenu);

        // create a group called root and add everything
        Group root = new Group();
        root.getChildren().addAll(
                menuBar,
                leaderboard,
                playersNameLabel,
                playersNameTextField,
                gameState,
                c1Cylinder,
                c2Cylinder,
                playGame,
                reset,
                r1,
                r2
        );

        // add the Group "root" to the Scene "scene" then add Scene to the Stage and show the stage
        Scene scene = new Scene(root, 700, 600, true);
        scene.setFill(Color.SLATEGREY);
        stage.setTitle("Two Up Game - Remastered");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private String getCurrentBet() {
        return selectedRadioBtn;
    }
}
