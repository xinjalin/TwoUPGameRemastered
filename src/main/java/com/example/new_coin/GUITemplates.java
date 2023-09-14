package com.example.new_coin;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GUITemplates {

    public static Label customLabel(String labelTxt, int posX, int posY) {
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: Yellow; -fx-padding: 10;");
        label.setText(labelTxt);
        label.setLayoutX(posX);
        label.setLayoutY(posY);
        label.setMinSize(100, 40);
        return label;
    }

    public static TextField customTextField(int posX, int posY) {
        TextField textField = new TextField();
        textField.setLayoutX(posX);
        textField.setLayoutY(posY);
        textField.setMinSize(200, 40);
        return textField;
    }

    public static Button customBtn(String btnText, int posX, int posY) {
        Button button = new Button(btnText);
        button.setLayoutX(posX);
        button.setLayoutY(posY);
        button.setMinSize(100, 40);
        return button;
    }

    public static RadioButton customRadioBtn(String btnText, int posX, int posY) {
        RadioButton r1 = new RadioButton(btnText);
        r1.setLayoutX(posX);
        r1.setLayoutY(posY);
        return r1;
    }
}
