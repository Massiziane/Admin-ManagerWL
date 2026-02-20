package org.example.workoutlog.utils.Add;

import javafx.scene.control.TableView;

public class AddUtils {

    /**
     * Adds an object to the database using its DAO and optionally refreshes a TableView.
     * @param dao   The DAO with an add method (e.g., CategoryDAO)
     * @param obj   The object to insert
     * @param tableView Optional TableView to refresh (can be null)
     * @param <T>   The model type
     */
    public static <T> void add(Object dao, T obj, TableView<Object> tableView) {
        try {
            // Call add method reflectively (all DAOs have addX(obj))
            dao.getClass().getMethod("add" + obj.getClass().getSimpleName(), obj.getClass())
                .invoke(dao, obj);

            // Refresh table if provided
            if (tableView != null) {
                // Assumes DAO has getAllX() method
                Object data = dao.getClass().getMethod(getAllMethodName(obj)).invoke(dao);
                if (data instanceof java.util.List<?> list) {
                    tableView.getItems().setAll(list);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getAllMethodName(Object obj) {
        return switch (obj.getClass().getSimpleName()) {
            case "Category" -> "getAllCategories";
            case "MuscleGroup" -> "getAllMuscleGroups";
            case "SetTemplate" -> "getAllSetTemplates";
            case "WorkoutExercise" -> "getAllWorkoutExercises";
            case "WorkoutSet" -> "getAllWorkoutSets";
            case "User" -> "getAllUsers";
            case "Program" -> "getAllPrograms";
            case "Workout" -> "getAllWorkouts";
            case "Exercise" -> "getAllExercises";
            default -> "getAll" + obj.getClass().getSimpleName() + "s";
        };
    }
}