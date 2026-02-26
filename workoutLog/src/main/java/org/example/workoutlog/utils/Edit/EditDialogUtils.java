package org.example.workoutlog.utils.Edit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class EditDialogUtils {

    /**
     * Opens a dialog to edit all fields of the given object, except the "id" field.
     * Uses reflection to dynamically populate the dialog with TextFields.
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
            List<Control> inputList = new ArrayList<>();

            // Create the GridPane form using EditDialogHelper
            dialog.getDialogPane().setContent(
                EditDialogHelper.createGridPane(obj, fieldList, inputList)
            );

            // Convert dialog input into the updated object when "Save" is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    try {
                        for (int i = 0; i < fieldList.size(); i++) {
                            Field field = fieldList.get(i);
                            Control control = inputList.get(i);
                            Class<?> type = field.getType();

                            Object value = null;

                            // Convert string input to appropriate type before setting the field
                            if (control instanceof TextField tf) {
                                String text = tf.getText();

                                if (type == int.class || type == Integer.class) {
                                    value = Integer.parseInt(text);
                                } else if (type == double.class || type == Double.class) {
                                    value = Double.parseDouble(text);
                                } else {
                                    value = text; // default to String
                                }
                            }
                            else if (control instanceof ComboBox<?> cb) {
                                value = cb.getValue();
                            }
                            else if (control instanceof CheckBox chk) {
                                value = chk.isSelected();
                            }

                            field.set(obj, value);
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