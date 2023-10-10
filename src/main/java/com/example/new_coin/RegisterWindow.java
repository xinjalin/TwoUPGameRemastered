package com.example.new_coin;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class RegisterWindow {

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Register Window");

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

        // register a new user
        Button registerButton = new Button("Register New User");
        GridPane.setConstraints(registerButton, 1, 2);
        registerButton.setOnAction(e -> {
            String registeringUsername = usernameInput.getText().trim();
            String registeringPassword = passwordInput.getText().trim();

            if (isEmpty(registeringUsername) || isEmpty(registeringPassword)) {
                infoLabel.setText("Username and password are required!");

            } else {
                // check for existing user
                if (SQL.checkDuplicateUser(registeringUsername)) {
                    infoLabel.setText("Username all ready in use");
                } else {
                    String hashedPassword = HashingService.hashPassword(registeringPassword);
                    User registeringUser = new User(registeringUsername, hashedPassword);

                    try {
                        SQL.addUser(registeringUser);
                        // close current window if successful inset into the database
                        // go back to log in window
                        primaryStage.close();
                        LoginWindow lw = new LoginWindow();
                        Stage n = new Stage();
                        lw.display(n);

                    } catch (RuntimeException ex) {
                        infoLabel.setText("Failed to register. Database error");
                        ex.printStackTrace();
                    }
                }
            }
        });

        // go back to log in window
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 3);
        loginButton.setOnAction(e -> {
            primaryStage.close();
            LoginWindow lw = new LoginWindow();
            Stage n = new Stage();
            lw.display(n);

        });


        grid.getChildren().addAll(
                usernameLabel,
                usernameInput,
                passwordLabel,
                passwordInput,
                registerButton,
                loginButton,
                infoLabel
        );
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Boolean isEmpty(String str) {
        boolean result;

        if (Objects.equals(str, "")) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
