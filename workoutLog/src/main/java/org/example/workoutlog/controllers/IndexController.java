package org.example.workoutlog.controllers;

import org.example.workoutlog.WorkoutLog;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.model.User;

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

        UserDAO userDao = new UserDAO();
        User admin = userDao.authAdmin(username, password);

        if (admin != null) {
            WorkoutLog.changePage("/org/example/workoutlog/assets/views/Dashboard.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login failed");
            alert.setContentText("Username or password is incorrect.");
            alert.showAndWait();
        }
    }
}