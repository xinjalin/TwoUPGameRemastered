package com.example.new_coin;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow {

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Login Window");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label infoLabel = new Label("");
        GridPane.setConstraints(infoLabel, 1, 4);

        Label usernameLabel = new Label("Username");
        GridPane.setConstraints(usernameLabel, 0, 0);

        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // log a user in and open game window
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String authUsername = usernameInput.getText();
            String authPassword = passwordInput.getText();

            // handle auth
            try {
                User authUser = SQL.getUserByUsername(authUsername);

                if (authUser == null) {
                    infoLabel.setText("no user found");

                } else {
                    Boolean isMatch = HashingService.checkPassword(authPassword, authUser.getPasswordHash());

                    if (isMatch) {
                        // open game window
                        System.out.println("successful auth of user");
                        primaryStage.close();
                        GameWindow gw = new GameWindow();
                        Stage n = new Stage();
                        gw.display(n);
                    } else {
                        infoLabel.setText("Invalid Password");
                    }
                }
            } catch (RuntimeException ex) {
                infoLabel.setText("Auth Error");
                ex.printStackTrace();
            }
        });

        // open register window
        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> {
            primaryStage.close();
            RegisterWindow rw = new RegisterWindow();
            Stage n = new Stage();
            rw.display(n);
        });


        grid.getChildren().addAll(
                usernameLabel,
                usernameInput,
                passwordLabel,
                passwordInput,
                loginButton,
                registerButton,
                infoLabel
        );

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
