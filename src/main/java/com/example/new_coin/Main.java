package com.example.new_coin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // create a cylinder with a default texture
        Cylinder cylinder = new Cylinder(50, 10);
        cylinder.setTranslateX(100);
        cylinder.setTranslateY(100);
        cylinder.setRotate(90);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image("coin_0.png"));
        cylinder.setMaterial(material);

        // create a button to change the texture
        Button button = new Button("Change Texture");
        button.setOnAction(event -> {
            // load a new texture image
            Image newTexture = new Image("coin_4.png");
            material.setDiffuseMap(newTexture);
        });
        // create a stack pane to hold the cylinder and the button
        StackPane root = new StackPane();
        root.getChildren().addAll(cylinder, button);
        // create a scene and show it
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
