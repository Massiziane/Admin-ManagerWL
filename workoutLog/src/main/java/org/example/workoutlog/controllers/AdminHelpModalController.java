package org.example.workoutlog.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminHelpModalController {

    @FXML
    private Button btnClose;

    @FXML
    public void initialize() {
        btnClose.setOnAction(e -> {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        });
    }
}