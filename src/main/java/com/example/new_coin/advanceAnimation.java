package com.example.new_coin;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class advanceAnimation extends Application {

    @Override
    public void start(Stage stage) {

        Cylinder cylinder = new Cylinder(100, 5); // Create a cylinder with the dimensions of a coin
        cylinder.setTranslateX(200); // Position the cylinder
        cylinder.setTranslateY(200); // Position the cylinder
        cylinder.setTranslateZ(100); // Position the cylinder
        cylinder.setRotationAxis(Rotate.X_AXIS); // Set the rotation axis

        // coin textures
        Image coinHeadsTexture = new Image("coin_4.png");
        Image coinTailsTexture = new Image("coin_0.png");

        // applying coin texture to a material that then gets applied to the cylinder
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(coinHeadsTexture);
        cylinder.setMaterial(material);

        //----------------------------------------------//
        //             sick animation skills            //
        //----------------------------------------------//
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(cylinder.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(cylinder.rotateProperty(), 360))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double angle = cylinder.getRotate();
            if (angle >= 0 && angle < 180) {
                material.setDiffuseMap(coinTailsTexture);
            } else {
                material.setDiffuseMap(coinHeadsTexture);
            }
        });

        timeline.play();
        //----------------------------------------------//

        // Create a button to change the texture
        Button button = new Button("Change Texture");
        button.setOnAction(e -> {
            timeline.stop(); // stop transition

            double currentAngle = cylinder.getRotate(); // storing value of current rotation angle
            double angleToComplete = (360 - currentAngle); // calculating the remaining angle of rotation to complete the rotation

            RotateTransition rotateToComplete = new RotateTransition(Duration.seconds(0.5), cylinder);
            rotateToComplete.setByAngle(angleToComplete + 360 + 90); // using the calculated result of angle to complete a rotation then add one 360 deg of rotation plus 90 deg of rotation to show one of the end faces of the cylinder
            rotateToComplete.play(); // play transition

            // Load a new texture image
            material.setDiffuseMap(coinTailsTexture);
        });

        Button resumeTimeline = new Button("Resume Animation");
        resumeTimeline.setTranslateX(100);
        resumeTimeline.setTranslateY(300);
        resumeTimeline.setOnAction(e -> {
            timeline.play();
        });

        // Add the cylinder to a group and create a scene
        Group root = new Group();
        root.getChildren().addAll(cylinder, button, resumeTimeline);
        Scene scene = new Scene(root, 400, 400, true);

        // Set the stage and show the scene
        stage.setTitle("Two Up Game - Remastered");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

