package org.example.workoutlog.controllers;

import org.example.workoutlog.WorkoutLog;

import javafx.fxml.FXML;


public class IndexController {

    @FXML
    private void login() {
        // Change to Dashboard scene
        WorkoutLog.changePage("/org/example/workoutlog/assets/views/Dashboard.fxml");
    }
}

