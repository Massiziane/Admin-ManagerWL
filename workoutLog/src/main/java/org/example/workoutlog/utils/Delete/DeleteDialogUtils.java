package org.example.workoutlog.utils.Delete;

public class DeleteDialogUtils {

    /**
     * Opens a confirmation dialog for the given object.
     */
    public static boolean confirmDelete(Object obj) {
        if (obj == null) return false;

        String name = obj.getClass().getSimpleName();
        return DeleteDialogHelper.showConfirmation(name);
    }
}