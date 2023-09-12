package com.example.new_coin;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;

public class App extends Application {
    private String selectedBet = "";
    public void start(Stage stage) {

        // coin textures
        Image coinHeadsTexture = new Image("coin_4.png");
        Image coinTailsTexture = new Image("coin_0.png");

        // cylinder material
        PhongMaterial material = new PhongMaterial();

        // create a cylinder with the dimensions of a coin
        Cylinder coinOneCylinder = CoinAnimation.coinModel(material, coinHeadsTexture, 200, 200, 100);
        Cylinder coinTwoCylinder = CoinAnimation.coinModel(material, coinHeadsTexture, 500, 200, 100);

        // create an animation loop for the cylinder
        Timeline timelineOne = CoinAnimation.createSpinningTimeline(coinOneCylinder, material, coinHeadsTexture, coinTailsTexture);
        timelineOne.play();
        Timeline timelineTwo = CoinAnimation.createSpinningTimeline(coinTwoCylinder, material, coinHeadsTexture, coinTailsTexture);
        timelineTwo.play();

        // select a bet radio button group
        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = CoinAnimation.radioBtn("Heads Heads", 200, 350);
        RadioButton r2 = CoinAnimation.radioBtn("Tails Tails", 400, 350);

        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);

        tg.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            RadioButton rb = (RadioButton)tg.getSelectedToggle();
            if (rb != null) {
                selectedBet = rb.getText();
                System.out.println(selectedBet);
            }
        });

        // buttons to play game
        Button playGame = CoinAnimation.handlePlayGame("Play Game" ,timelineOne, timelineTwo, coinOneCylinder, coinTwoCylinder, selectedBet, 300, 400);


        // create a group called root and add everything
        Group root = new Group();
        root.getChildren().addAll(coinOneCylinder, coinTwoCylinder, playGame, r1, r2);

        // add the Group "root" to the Scene "scene" then add Scene to the Stage and show the stage
        Scene scene = new Scene(root, 700, 600, true);
        stage.setTitle("Two Up Game - Remastered");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        // start application
        launch(args);
    }
}
