package org.example.workoutlog.controllers;

import org.example.workoutlog.WorkoutLog;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IndexController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "admin".equals(password)) {
            WorkoutLog.changePage("/org/example/workoutlog/assets/views/Dashboard.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login failed");
            alert.setContentText("Username or password is incorrect.");
            alert.showAndWait();
        }
    }
}