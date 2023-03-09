package com.example.new_coin;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
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

public class Main_animation extends Application {

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

        // applying coin texture to the cylinder
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(coinHeadsTexture);
        cylinder.setMaterial(material);

        // Create a rotation animation
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), cylinder);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setAutoReverse(false);
        rotate.play();

        // Create a button to change the texture
        Button button = new Button("Change Texture");
        button.setOnAction(e -> {
            rotate.stop(); // stop transition

            double currentAngle = cylinder.getRotate(); // storing value of current rotation angle
            double angleToComplete = (360 - currentAngle); // calculating the remaining angle of rotation to complete the rotation

            RotateTransition rotateToComplete = new RotateTransition(Duration.seconds(0.5), cylinder);
            rotateToComplete.setByAngle(angleToComplete + 360 + 90); // using the calculated result of angle to complete a rotation then add one 360 deg of rotation plus 90 deg of rotation to show one of the end faces of the cylinder
            rotateToComplete.play(); // play transition

            // Load a new texture image
            material.setDiffuseMap(coinTailsTexture);
        });

        // Add the cylinder to a group and create a scene
        Group root = new Group();
        root.getChildren().addAll(cylinder, button);
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


