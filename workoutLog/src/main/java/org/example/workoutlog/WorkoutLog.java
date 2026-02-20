package org.example.workoutlog;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorkoutLog extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // set primary stage here

        FXMLLoader fxmlLoader = new FXMLLoader(WorkoutLog.class.getResource("/org/example/workoutlog/assets/views/index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Workout Admin");
        stage.setScene(scene);
        stage.show();
    }

    public static void changePage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(WorkoutLog.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 1500, 800);
            primaryStage.setScene(scene);
            primaryStage.show(); // make sure stage is visible
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static class changePage {

        public changePage() {
        }
    }
}
