package org.example.workoutlog.utils.Delete;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DeleteDialogHelper {

    /**
     * Shows a confirmation dialog for deletion.
     * @param itemName Name of the item to delete (e.g., "Category")
     * @return true if user confirms, false otherwise
     */
    public static boolean showConfirmation(String itemName) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this " + itemName + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}