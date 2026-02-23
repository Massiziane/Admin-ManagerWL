package org.example.workoutlog.controllers;

import java.util.HashMap;
import java.util.Map;

import org.example.workoutlog.WorkoutLog;
import org.example.workoutlog.dao.CategoryDAO;
import org.example.workoutlog.dao.ExerciseDAO;
import org.example.workoutlog.dao.MuscleGroupDAO;
import org.example.workoutlog.dao.ProgramDAO;
import org.example.workoutlog.dao.SetTemplateDAO;
import org.example.workoutlog.dao.UserDAO;
import org.example.workoutlog.dao.WorkoutDAO;
import org.example.workoutlog.dao.WorkoutExerciseDAO;
import org.example.workoutlog.dao.WorkoutSetDAO;
import org.example.workoutlog.model.Category;
import org.example.workoutlog.model.Exercise;
import org.example.workoutlog.model.MuscleGroup;
import org.example.workoutlog.model.Program;
import org.example.workoutlog.model.SetTemplate;
import org.example.workoutlog.model.User;
import org.example.workoutlog.model.Workout;
import org.example.workoutlog.model.WorkoutExercise;
import org.example.workoutlog.model.WorkoutSet;
import org.example.workoutlog.utils.Add.AddDialogUtils;
import org.example.workoutlog.utils.Add.AddUtils;
import org.example.workoutlog.utils.Delete.DeleteDialogUtils;
import org.example.workoutlog.utils.Delete.DeleteUtils;
import org.example.workoutlog.utils.Edit.EditDialogUtils;
import org.example.workoutlog.utils.Edit.EditUtils;
import org.example.workoutlog.utils.TableUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the Dashboard view.
 * 
 * This class handles user interactions from the dashboard interface, 
 * including navigating between entity tables (Users, Programs, Workouts, etc.),
 * and managing CRUD operations (Add, Edit, Delete).
 */
public class DashboardController {

    // Root pane of the dashboard view
    @FXML private AnchorPane rootPane;

    // Buttons for navigation and CRUD actions
    @FXML private Button logoutButton;
    @FXML private Button btnAdd, btnEdit, btnDelete;

    // Buttons for switching between data tables
    @FXML private Button btnUsers, btnPrograms, btnWorkouts, btnExercises,
            btnCategories, btnMuscleGroups, btnSetTemplates,
            btnWorkoutExercises, btnWorkoutSets;

    // TableView to display current entity data
    @FXML private TableView<Object> mainTableView;

    // Label showing which table is currently displayed
    @FXML private Label tableShown;

    // DAO instances for all main data models
    private final UserDAO userDAO = new UserDAO();
    private final ProgramDAO programDAO = new ProgramDAO();
    private final WorkoutDAO workoutDAO = new WorkoutDAO();
    private final ExerciseDAO exerciseDAO = new ExerciseDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final MuscleGroupDAO muscleGroupDAO = new MuscleGroupDAO();
    private final SetTemplateDAO setTemplateDAO = new SetTemplateDAO();
    private final WorkoutExerciseDAO workoutExerciseDAO = new WorkoutExerciseDAO();
    private final WorkoutSetDAO workoutSetDAO = new WorkoutSetDAO();

    // Map storing correspondence between model classes and their DAO objects
    private Map<Class<?>, Object> daoMap;

    /**
     * Initializes the dashboard view.
     * 
     * This method is automatically called after FXML loading.
     * It assigns button actions, sets up table-switching logic,
     * and configures CRUD event handlers.
     */
    @FXML
    public void initialize() {

        // Handles logout button click
        logoutButton.setOnAction(e -> logout());

        // Bind each menu button to its corresponding DAO retrieval & table load
        btnUsers.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, userDAO.getAllUsers());
            tableShown.setText("Table:   Users"); 
        });
        btnPrograms.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, programDAO.getAllPrograms());
            tableShown.setText("Table:   Programs"); 
        });
        btnWorkouts.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, workoutDAO.getAllWorkouts());
            tableShown.setText("Table:   Workouts"); 
        });
        btnWorkoutExercises.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, workoutExerciseDAO.getAllWorkoutExercises());
            tableShown.setText("Table:   Workout Exercises"); 
        });
        btnWorkoutSets.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, workoutSetDAO.getAllWorkoutSets());
            tableShown.setText("Table:   Workout Sets"); 
        });
        btnExercises.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, exerciseDAO.getAllExercises());
            tableShown.setText("Table:   Exercises"); 
        });
        btnCategories.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, categoryDAO.getAllCategories());
            tableShown.setText("Table:   Categories"); 
        });
        btnMuscleGroups.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, muscleGroupDAO.getAllMuscleGroups());
            tableShown.setText("Table:   Muscle Groups"); 
        });
        btnSetTemplates.setOnAction(e -> { 
            TableUtils.loadTable(mainTableView, setTemplateDAO.getAllSetTemplates());
            tableShown.setText("Table:   Set Templates"); 
        });

        // Populate DAO map for dynamic access during CRUD operations
        daoMap = new HashMap<>();
        daoMap.put(Category.class, categoryDAO);
        daoMap.put(Program.class, programDAO);
        daoMap.put(User.class, userDAO);
        daoMap.put(Workout.class, workoutDAO);
        daoMap.put(Exercise.class, exerciseDAO);
        daoMap.put(MuscleGroup.class, muscleGroupDAO);
        daoMap.put(SetTemplate.class, setTemplateDAO);
        daoMap.put(WorkoutExercise.class, workoutExerciseDAO);
        daoMap.put(WorkoutSet.class, workoutSetDAO);

        // ADD Button: opens dialog and inserts new entity
        btnAdd.setOnAction(e -> {
            if (mainTableView != null) {
                // Determine the class of the table based on the label
                String tableName = tableShown.getText().replace("Table: ", "").trim();
                Class<?> clazz = switch (tableName) {
                    case "Users" -> User.class;
                    case "Programs" -> Program.class;
                    case "Workouts" -> Workout.class;
                    case "Exercises" -> Exercise.class;
                    case "Categories" -> Category.class;
                    case "Muscle Groups" -> MuscleGroup.class;
                    case "Set Templates" -> SetTemplate.class;
                    case "Workout Exercises" -> WorkoutExercise.class;
                    case "Workout Sets" -> WorkoutSet.class;
                    default -> null;
                };

                if (clazz != null) {
                    Object dao = daoMap.get(clazz);
                    if (dao != null) {
                        Object newItem = AddDialogUtils.addWithDialog(clazz);
                        if (newItem != null) {
                            AddUtils.add(dao, newItem, null); // add without refreshing items

                            // Refresh table properly using TableUtils
                            switch (tableName) {
                                case "Users" -> TableUtils.loadTable(mainTableView, userDAO.getAllUsers());
                                case "Programs" -> TableUtils.loadTable(mainTableView, programDAO.getAllPrograms());
                                case "Workouts" -> TableUtils.loadTable(mainTableView, workoutDAO.getAllWorkouts());
                                case "Exercises" -> TableUtils.loadTable(mainTableView, exerciseDAO.getAllExercises());
                                case "Categories" -> TableUtils.loadTable(mainTableView, categoryDAO.getAllCategories());
                                case "Muscle Groups" -> TableUtils.loadTable(mainTableView, muscleGroupDAO.getAllMuscleGroups());
                                case "Set Templates" -> TableUtils.loadTable(mainTableView, setTemplateDAO.getAllSetTemplates());
                                case "Workout Exercises" -> TableUtils.loadTable(mainTableView, workoutExerciseDAO.getAllWorkoutExercises());
                                case "Workout Sets" -> TableUtils.loadTable(mainTableView, workoutSetDAO.getAllWorkoutSets());
                            }
                        }
                    }
                }
            }
        });
        // EDIT Button: opens edit dialog for selected entity
        btnEdit.setOnAction(e -> {
            if (mainTableView != null && !mainTableView.getSelectionModel().isEmpty()) {
                Object selected = mainTableView.getSelectionModel().getSelectedItem();
                Object dao = daoMap.get(selected.getClass());
                if (dao != null) {
                    Object editedItem = EditDialogUtils.editWithDialog(selected);
                    if (editedItem != null) {
                        EditUtils.edit(dao, editedItem, mainTableView);
                    }
                }
            }
        });

        // DELETE Button: confirms and removes selected entity
        btnDelete.setOnAction(e -> {
            if (mainTableView.getSelectionModel().getSelectedItem() != null) {
                Object selected = mainTableView.getSelectionModel().getSelectedItem();
                Object dao = daoMap.get(selected.getClass());

                // Confirm deletion before proceeding
                if (DeleteDialogUtils.confirmDelete(selected)) {
                    DeleteUtils.delete(dao, selected, mainTableView);
                }
            }
        });
    }

    /**
     * Logs out the current user and returns to the login/index page.
     */
    private void logout() {
        WorkoutLog.changePage("/org/example/workoutlog/assets/views/index.fxml");
    }
}
