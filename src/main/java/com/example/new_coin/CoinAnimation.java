package com.example.new_coin;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.lang.reflect.Array;

public class CoinAnimation {
    public void welcomeMSG() {
        System.out.println("Welcome to Two Up in JavaFX");
    }
    public static Cylinder coinModel(PhongMaterial material, Image texture, int posX, int posY, int posZ) {
        Cylinder cylinder = new Cylinder(100,5);
        cylinder.setTranslateX(posX); // Position the cylinder
        cylinder.setTranslateY(posY); // Position the cylinder
        cylinder.setTranslateZ(posZ); // Position the cylinder
        cylinder.setRotationAxis(Rotate.X_AXIS); // Set the rotation axis

        // applying coin texture to a material that then gets applied to the cylinder
        material.setDiffuseMap(texture);
        cylinder.setMaterial(material);

        return cylinder;
    }

    public static Timeline createSpinningTimeline(Cylinder cylinder, PhongMaterial material, Image textureHeads, Image textureTails) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(cylinder.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(cylinder.rotateProperty(), 360))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double angle = cylinder.getRotate();
            if (angle >= 0 && angle < 180) {
                material.setDiffuseMap(textureTails);
            } else {
                material.setDiffuseMap(textureHeads);
            }
        });
        return timeline;
    }

    public static Button handlePlayGame(String btnText, Timeline timelineOne, Timeline timelineTwo, Cylinder cylinderOne, Cylinder cylinderTwo, String selectedBet, int posX, int posY) {
        Button button = new Button(btnText);
        button.setLayoutX(posX);
        button.setLayoutY(posY);

        button.setOnAction(e -> {
            timelineOne.stop(); // stop transition
            timelineTwo.stop(); // stop transition

            double currentAngle = cylinderOne.getRotate(); // storing value of current rotation angle
            double angleToComplete = (360 - currentAngle); // calculating the remaining angle of rotation to complete the rotation

            RotateTransition rotateToCompleteOne = new RotateTransition(Duration.seconds(0.5), cylinderOne);
            rotateToCompleteOne.setByAngle(angleToComplete + 360 + 90); // using the calculated result of angle to complete a rotation then add one 360 deg of rotation plus 90 deg of rotation to show one of the end faces of the cylinder
            rotateToCompleteOne.play(); // play transition

            RotateTransition rotateToCompleteTwo = new RotateTransition(Duration.seconds(0.5), cylinderTwo);
            rotateToCompleteTwo.setByAngle(angleToComplete + 360 + 90); // using the calculated result of angle to complete a rotation then add one 360 deg of rotation plus 90 deg of rotation to show one of the end faces of the cylinder
            rotateToCompleteTwo.play(); // play transition

            Coin coinOne = new Coin();
            Coin coinTwo = new Coin();

            coinOne.flip();
            coinTwo.flip();

            Game game = new Game();
            String result = game.gameOfTwoUp(coinOne.isHeads(), coinTwo.isHeads(), selectedBet, "Testing");
            System.out.println("Game Result:" + result);

            // Load a new texture image
            // material.setDiffuseMap(winning texture);
        });

        return button;
    }

    public static RadioButton radioBtn(String btnText, int posX, int posY) {
        RadioButton r1 = new RadioButton(btnText);
        r1.setLayoutX(posX);
        r1.setLayoutY(posY);
        return r1;
    }
}
