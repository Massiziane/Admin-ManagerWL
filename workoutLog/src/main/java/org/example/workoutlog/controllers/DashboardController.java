package org.example.workoutlog.controllers;

import java.lang.reflect.Field;
import java.util.List;

import org.example.workoutlog.dao.CategoryDAO;
import org.example.workoutlog.dao.ExerciseDAO;
import org.example.workoutlog.dao.MuscleGroupDAO;
import org.example.workoutlog.dao.ProgramDAO;
import org.example.workoutlog.dao.SetTemplateDAO;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.dao.WorkoutDAO;
import org.example.workoutlog.dao.WorkoutExerciseDAO;
import org.example.workoutlog.dao.WorkoutSetDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;





public class DashboardController {

    @FXML private AnchorPane rootPane;

    @FXML private Button logoutButton;
    @FXML private Button btnAdd, btnEdit, btnDelete;

    @FXML private Button btnUsers, btnPrograms, btnWorkouts, btnExercises,
            btnCategories, btnMuscleGroups, btnSetTemplates,
            btnWorkoutExercises, btnWorkoutSets;

    @FXML private TableView<Object> mainTableView;

    private final UserDAO userDAO = new UserDAO();
    private final ProgramDAO programDAO = new ProgramDAO();
    private final WorkoutDAO workoutDAO = new WorkoutDAO();
    private final ExerciseDAO exerciseDAO = new ExerciseDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final MuscleGroupDAO muscleGroupDAO = new MuscleGroupDAO();
    private final SetTemplateDAO setTemplateDAO = new SetTemplateDAO();
    private final WorkoutExerciseDAO workoutExerciseDAO = new WorkoutExerciseDAO();
    private final WorkoutSetDAO workoutSetDAO = new WorkoutSetDAO();
    

    @FXML
    public void initialize() {
        // Attach click handlers
        btnUsers.setOnAction(e -> loadTable(userDAO.getAllUsers()));
        btnPrograms.setOnAction(e -> loadTable(programDAO.getAllPrograms()));
        btnWorkouts.setOnAction(e -> loadTable(workoutDAO.getAllWorkouts()));
        btnWorkoutExercises.setOnAction(e -> loadTable(workoutExerciseDAO.getAllWorkoutExercises()));
        btnWorkoutSets.setOnAction(e -> loadTable(workoutSetDAO.getAllWorkoutSets()));
        btnExercises.setOnAction(e -> loadTable(exerciseDAO.getAllExercises()));
        btnCategories.setOnAction(e -> loadTable(categoryDAO.getAllCategories()));
        btnMuscleGroups.setOnAction(e -> loadTable(muscleGroupDAO.getAllMuscleGroups()));
        btnSetTemplates.setOnAction(e -> loadTable(setTemplateDAO.getAllSetTemplates()));

    }

    /**
     * Dynamically populate TableView based on any list of objects.
     */
    private void loadTable(List<?> items) {
        mainTableView.getItems().clear();
        mainTableView.getColumns().clear();

        if (items.isEmpty()) return;

        Object sample = items.get(0);

        for (Field field : sample.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            TableColumn<Object, Object> column = new TableColumn<>(capitalize(field.getName()));
            column.setCellValueFactory(cellData -> {
                try {
                    return new javafx.beans.property.SimpleObjectProperty<>(field.get(cellData.getValue()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            });
            mainTableView.getColumns().add(column);
        }

        ObservableList<Object> data = FXCollections.observableArrayList(items);
        mainTableView.setItems(data);
    }

    // Capitalize first letter for column headers
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
