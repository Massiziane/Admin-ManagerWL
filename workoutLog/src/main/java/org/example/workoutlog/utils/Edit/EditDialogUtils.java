package org.example.workoutlog.utils.Edit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class EditDialogUtils {

    /**
     * Opens a dialog to edit all fields of the given object, except the "id" field.
     * Uses reflection to dynamically populate the dialog with TextFields.
     *
     * @param obj The object to edit
     * @param <T> The type of the object
     * @return The updated object if "Save" is pressed, otherwise null
     */
    public static <T> T editWithDialog(T obj) {
        try {
            // Create a new dialog
            Dialog<T> dialog = new Dialog<>();
            dialog.setTitle("Edit " + obj.getClass().getSimpleName());

            // Add Save and Cancel buttons
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Prepare lists to hold reflected fields and corresponding TextField inputs
            List<Field> fieldList = new ArrayList<>();
            List<TextField> inputList = new ArrayList<>();

            // Create the GridPane form using EditDialogHelper
            dialog.getDialogPane().setContent(EditDialogHelper.createGridPane(obj, fieldList, inputList));

            // Convert dialog input into the updated object when "Save" is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    try {
                        for (int i = 0; i < fieldList.size(); i++) {
                            Field field = fieldList.get(i);
                            String text = inputList.get(i).getText();
                            Class<?> type = field.getType();

                            // Convert string input to appropriate type before setting the field
                            if (type == int.class || type == Integer.class) {
                                field.set(obj, Integer.parseInt(text));
                            } else if (type == double.class || type == Double.class) {
                                field.set(obj, Double.parseDouble(text));
                            } else if (type == boolean.class || type == Boolean.class) {
                                field.set(obj, Boolean.parseBoolean(text));
                            } else {
                                field.set(obj, text); // default to String
                            }
                        }
                        return obj; // return updated object
                    } catch (Exception e) {
                        e.printStackTrace(); // log any errors during conversion
                    }
                }
                return null; // return null if Cancel clicked or error occurs
            });

            // Show dialog and return result (or null if canceled)
            return dialog.showAndWait().orElse(null);

        } catch (Exception e) {
            e.printStackTrace(); // catch unexpected errors creating or showing dialog
            return null;
        }
    }
}