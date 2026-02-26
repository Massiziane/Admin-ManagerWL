package org.example.workoutlog.utils.Edit;

import javafx.scene.control.TableView;

public class EditUtils {

    /**
     * Updates an object in the database using its DAO and refreshes a TableView.
     */
    public static <T> void edit(Object dao, T obj, TableView<Object> tableView) {
        try {
            // Use reflection to call the DAO's update method (e.g., updateCategory(Category))
            dao.getClass()
               .getMethod("update" + obj.getClass().getSimpleName(), obj.getClass())
               .invoke(dao, obj);

            // Refresh the TableView if provided
            if (tableView != null) {
                // Use reflection to call the DAO's "getAll" method for the object type
                Object data = dao.getClass().getMethod(getAllMethodName(obj)).invoke(dao);

                // Update TableView items if the returned data is a List
                if (data instanceof java.util.List<?> list) {
                    tableView.getItems().setAll(list);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // log any reflection or database errors
        }
    }

    /**
     * Returns the DAO "getAll" method name for a given object type.
     * Used to refresh the TableView after editing.
     *
     * @param obj The object to determine DAO method for
     * @return Method name string (e.g., "getAllCategories")
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