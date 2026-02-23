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
        primaryStage = stage; // store reference to primary stage

        // Load the FXML file for the index/login page
        FXMLLoader fxmlLoader = new FXMLLoader(
                WorkoutLog.class.getResource("/org/example/workoutlog/assets/views/index.fxml")
        );

        // Create the scene with width=1500 and height=800
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);

        // Apply the CSS file to style the scene
        scene.getStylesheets().add(
                WorkoutLog.class.getResource("/org/example/workoutlog/assets/css/Index.css").toExternalForm()
        );

        // Configure the primary stage
        stage.setTitle("Workout Admin");
        stage.setScene(scene);
        stage.show();
    }

    public static void changePage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(WorkoutLog.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 1500, 800);

            // Apply the CSS again for the new scene
            scene.getStylesheets().add(
                    WorkoutLog.class.getResource("/org/example/workoutlog/assets/css/Index.css").toExternalForm()
            );

            primaryStage.setScene(scene);
            primaryStage.show(); // make sure stage is visible
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