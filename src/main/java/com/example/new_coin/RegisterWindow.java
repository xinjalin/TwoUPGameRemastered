package com.example.new_coin;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterWindow {

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Register Window");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label usernameLabel = new Label("Username");
        GridPane.setConstraints(usernameLabel, 0, 0);

        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        Button registerButton = new Button("Register New User");
        GridPane.setConstraints(registerButton, 1, 2);
        registerButton.setOnAction(e -> {
            String registeringUsername = passwordInput.getText().trim();
            String registeringPassword = passwordInput.getText().trim();

            if (isEmpty(registeringUsername) || isEmpty(registeringPassword)) {
                // change this to label text
                System.out.println("Username and password are required!");
            }

            String hashedPassword = HashingService.hashPassword(registeringPassword);
            User registeringUser = new User(registeringUsername, hashedPassword);

            try {
                SQL.addUser(registeringUser);
                // close current window if successful inset into the database
                primaryStage.close();
                LoginWindow lw = new LoginWindow();
                Stage n = new Stage();
                lw.display(n);

            } catch (RuntimeException ex) {
                System.out.println("Failed to register the user. Please try again later.");
                ex.printStackTrace();
            }
        });


        grid.getChildren().addAll(
                usernameLabel,
                usernameInput,
                passwordLabel,
                passwordInput,
                registerButton
        );
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
