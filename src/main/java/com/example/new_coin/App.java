package com.example.new_coin;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;

public class App extends Application {
    public String selectedBet = "";
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
        Timeline timelineOne = CoinAnimation.createSpinningTimeline(
                c1Cylinder,
                c1material,
                coinHeadsTexture,
                coinTailsTexture
        );
        timelineOne.play();

        Timeline timelineTwo = CoinAnimation.createSpinningTimeline(
                c2Cylinder,
                c2material,
                coinHeadsTexture,
                coinTailsTexture
        );
        timelineTwo.play();

        // select a bet radio button group
        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = CoinAnimation.radioBtn("Heads Heads", 200, 350);
        RadioButton r2 = CoinAnimation.radioBtn("Tails Tails", 400, 350);

        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);

        r1.setOnAction(e -> {
            selectedBet = r1.getText();
            System.out.println(selectedBet); // debug
        });
        r2.setOnAction(e -> {
            selectedBet = r2.getText();
            System.out.println(selectedBet); // debug
        });

        // buttons to play game
        Button playGame = CoinAnimation.handleBtnAnimation(
                "Play Game",
                300,
                400
        );
        playGame.setOnAction(e -> {
            String currentBet = getCurrentBet();
            Coin c1 = new Coin();
            Coin c2 = new Coin();
            c1.flip();
            c2.flip();
            Image c1Texture = CoinAnimation.coinTexture(c1);
            Image c2Texture = CoinAnimation.coinTexture(c2);
//            System.out.println("coin 1: " + c1.isHeads()); // debug
//            System.out.println("coin 2: " + c2.isHeads()); // debug
            String gameResult = CoinAnimation.handleGame(c1, c2, currentBet, "Testing");
//            System.out.println(gameResult); // debug
            switch (gameResult) {
                case "HH", "HH Lose", "HH Flip Again", "TT", "TT Lose", "TT Flip Again" -> {
                    CoinAnimation.handleCoinAnimationStop(
                        timelineOne,
                        timelineTwo,
                        c1Cylinder,
                        c2Cylinder,
                        c1material,
                        c2material,
                        c1Texture,
                        c2Texture
                    );
                }
            }
        });


        // create a group called root and add everything
        Group root = new Group();
        root.getChildren().addAll(c1Cylinder, c2Cylinder, playGame, r1, r2);

        // add the Group "root" to the Scene "scene" then add Scene to the Stage and show the stage
        Scene scene = new Scene(root, 700, 600, true);
        stage.setTitle("Two Up Game - Remastered");
        stage.setScene(scene);
        stage.show();
    }

    private String getCurrentBet() {
        return selectedBet;
    }

    public static void main(String[] args) {
        // start application
        launch(args);
    }
}
