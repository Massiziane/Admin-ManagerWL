package org.example.workoutlog;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the WorkoutLog JavaFX application.
 * 
 * This class is responsible for:
 *  - Launching the primary stage
 *  - Loading FXML views
 *  - Applying CSS styling
 *  - Allowing page changes within the application
 */
public class WorkoutLog extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; 

        // Load the FXML file for the index/login page
        FXMLLoader fxmlLoader = new FXMLLoader(
                WorkoutLog.class.getResource("/org/example/workoutlog/assets/views/index.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        // Configure the primary stage
        stage.setTitle("Workout Admin");
        stage.setScene(scene);
        stage.show();
    }

    public static void changePage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(WorkoutLog.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 1500, 800);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML: " + fxmlPath);
        }
    }

    /**
     * Main method. Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}