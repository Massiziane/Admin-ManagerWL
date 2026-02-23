package org.example.workoutlog.utils.Add;

import javafx.scene.control.TableView;

/**
 * Utility class to handle adding objects to the database via their DAO
 * and automatically refreshing a TableView, even when empty.
 */
public class AddUtils {

    /**
     * Adds an object to the database using its DAO and refreshes the TableView if provided.
     *
     * @param dao       The DAO containing the appropriate add method (e.g., CategoryDAO)
     * @param obj       The object to insert into the database
     * @param tableView Optional TableView to refresh after insertion (can be null)
     * @param <T>       The type of the model object
     */
    public static <T> void add(Object dao, T obj, TableView<Object> tableView) {
        try {
            // Call the DAO's add method dynamically via reflection
            dao.getClass()
               .getMethod("add" + obj.getClass().getSimpleName(), obj.getClass())
               .invoke(dao, obj);

            // Always refresh the TableView if provided, even if it's empty
            if (tableView != null) {
                Object data = dao.getClass()
                                 .getMethod(getAllMethodName(obj))
                                 .invoke(dao);

                if (data instanceof java.util.List<?> list) {
                    tableView.getItems().setAll(list);  // refresh with updated data
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Determines the appropriate "getAll" method name for a given object type.
     */
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