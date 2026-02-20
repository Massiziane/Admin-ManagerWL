package org.example.workoutlog.controllers;

import java.util.HashMap;
import java.util.Map;

import org.example.workoutlog.dao.CategoryDAO;
import org.example.workoutlog.dao.ExerciseDAO;
import org.example.workoutlog.dao.MuscleGroupDAO;
import org.example.workoutlog.dao.ProgramDAO;
import org.example.workoutlog.dao.SetTemplateDAO;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.dao.WorkoutDAO;
import org.example.workoutlog.dao.WorkoutExerciseDAO;
import org.example.workoutlog.dao.WorkoutSetDAO;
import org.example.workoutlog.model.*;
import org.example.workoutlog.utils.TableUtils;
import org.example.workoutlog.utils.Add.AddDialogUtils;
import org.example.workoutlog.utils.Add.AddUtils;

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

    // Generic DAO map
    private Map<Class<?>, Object> daoMap;
    

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


        Map<Class<?>, Object> daoMap = new HashMap<>();
        daoMap.put(Category.class, categoryDAO);
        daoMap.put(Program.class, programDAO);
        daoMap.put(User.class, userDAO);
        daoMap.put(Workout.class, workoutDAO);
        daoMap.put(Exercise.class, exerciseDAO);
        daoMap.put(MuscleGroup.class, muscleGroupDAO);
        daoMap.put(SetTemplate.class, setTemplateDAO);
        daoMap.put(WorkoutExercise.class, workoutExerciseDAO);
        daoMap.put(WorkoutSet.class, workoutSetDAO);
        
        
        btnAdd.setOnAction(e -> {
            if (mainTableView != null && !mainTableView.getItems().isEmpty()) {
                Class<?> clazz = mainTableView.getItems().get(0).getClass();
                Object dao = daoMap.get(clazz);
                if (dao != null) {
                    Object newItem = AddDialogUtils.addWithDialog(clazz);
                    if (newItem != null) {
                        AddUtils.add(dao, newItem, mainTableView);
                    }
                }
            }
        });
    }
}
