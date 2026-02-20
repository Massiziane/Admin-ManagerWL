package org.example.workoutlog.controllers;

import org.example.workoutlog.dao.CategoryDAO;
import org.example.workoutlog.dao.ExerciseDAO;
import org.example.workoutlog.dao.MuscleGroupDAO;
import org.example.workoutlog.dao.ProgramDAO;
import org.example.workoutlog.dao.SetTemplateDAO;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.dao.WorkoutDAO;
import org.example.workoutlog.dao.WorkoutExerciseDAO;
import org.example.workoutlog.dao.WorkoutSetDAO;
import org.example.workoutlog.utils.TableUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        btnUsers.setOnAction(e -> TableUtils.loadTable(mainTableView, userDAO.getAllUsers()));
        btnPrograms.setOnAction(e -> TableUtils.loadTable(mainTableView, programDAO.getAllPrograms()));
        btnWorkouts.setOnAction(e -> TableUtils.loadTable(mainTableView, workoutDAO.getAllWorkouts()));
        btnWorkoutExercises.setOnAction(e -> TableUtils.loadTable(mainTableView, workoutExerciseDAO.getAllWorkoutExercises()));
        btnWorkoutSets.setOnAction(e -> TableUtils.loadTable(mainTableView, workoutSetDAO.getAllWorkoutSets()));
        btnExercises.setOnAction(e -> TableUtils.loadTable(mainTableView, exerciseDAO.getAllExercises()));
        btnCategories.setOnAction(e -> TableUtils.loadTable(mainTableView, categoryDAO.getAllCategories()));
        btnMuscleGroups.setOnAction(e -> TableUtils.loadTable(mainTableView, muscleGroupDAO.getAllMuscleGroups()));
        btnSetTemplates.setOnAction(e -> TableUtils.loadTable(mainTableView, setTemplateDAO.getAllSetTemplates()));
    }
}
