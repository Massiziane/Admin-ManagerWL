package org.example.workoutlog.utils.Delete;

public class DeleteDialogUtils {

    /**
     * Opens a confirmation dialog for the given object.
     * @param obj The object user wants to delete
     * @return true if user confirmed, false otherwise
     */
    public static boolean confirmDelete(Object obj) {
        if (obj == null) return false;

        String name = obj.getClass().getSimpleName();
        return DeleteDialogHelper.showConfirmation(name);
    }
}