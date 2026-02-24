package org.example.workoutlog.controllers;

import org.example.workoutlog.WorkoutLog;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for the application’s Index (login) view.
 *
 * This class manages the login process. It retrieves the user’s input from
 * the username and password fields, authenticates the credentials using
 * {@link UserDAO}, and navigates to the dashboard if the login is successful.
 */
public class IndexController {

    /** Text field for entering the username. */
    @FXML
    private TextField usernameField;

    /** Password field for entering the password. */
    @FXML
    private PasswordField passwordField;

    /**
     * Handles the login button action.
     *
     * This method retrieves the input values from the username and password
     * fields, verifies the credentials through the {@link UserDAO#authAdmin(String, String)}  
     * method, and performs one of two actions:
     *     If authentication is successful, it redirects to the dashboard view.</li>
     *     If authentication fails, it displays an error alert to the user.
     */
    @FXML
    private void login() {
        // Retrieve entered username and password
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Initialize the data access object and attempt admin authentication
        UserDAO userDao = new UserDAO();
        User admin = userDao.authAdmin(username, password);

        // If credentials are valid, load the dashboard
        if (admin != null) {
            WorkoutLog.changePage("/org/example/workoutlog/assets/views/Dashboard.fxml");
        } 
        // Otherwise, show an error alert
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login failed");
            alert.setContentText("Username or password is incorrect.");
            alert.showAndWait();
        }
    }
}
