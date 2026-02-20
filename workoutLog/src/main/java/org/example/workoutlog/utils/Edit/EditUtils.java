package org.example.workoutlog.utils.Edit;

import javafx.scene.control.TableView;

public class EditUtils {

    /**
     * Calls the DAO's update method and refreshes the TableView.
     */
    public static <T> void edit(Object dao, T obj, TableView<Object> tableView) {
        try {
            // Call update method (updateX(obj))
            dao.getClass().getMethod("update" + obj.getClass().getSimpleName(), obj.getClass())
                .invoke(dao, obj);

            // Refresh table
            if (tableView != null) {
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