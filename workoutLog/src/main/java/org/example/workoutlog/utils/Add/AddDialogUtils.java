package org.example.workoutlog.utils.Add;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Utility class for generating dynamic "Add" dialogs using Java reflection.
 *
 * This class automatically creates a modal dialog with input fields for all
 * non-ID fields of any model class. It supports common Java types (String, int,
 * double, boolean) and uses reflection to populate the object when the user
 * clicks "Add".
 */
public class AddDialogUtils {

    public static <T> T addWithDialog(Class<T> clazz) {
        try {
            // Create a new instance of the model class using default constructor
            T newObj = clazz.getDeclaredConstructor().newInstance();

            // Configure the modal dialog
            Dialog<T> dialog = new Dialog<>();
            dialog.setTitle("Add " + clazz.getSimpleName());

            // Add custom "Add" button and standard Cancel button
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create responsive grid layout for input fields
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Get all declared fields from the class (excluding id)
            Field[] fields = clazz.getDeclaredFields();
            int row = 0;

            // Lists to track field mappings and their corresponding input controls
            List<Field> fieldList = new ArrayList<>();
            List<Control> inputList = new ArrayList<>();

            // Dynamically generate input fields for each non-ID field
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                if (field.getName().equalsIgnoreCase("id")) continue; // Skip ID field

                // Create label and input field for current property
                Label label = new Label(capitalize(field.getName()) + ":");

                // Special case for "role" field → show dropdown instead of TextField
                if (field.getName().equalsIgnoreCase("role")) {

                    ComboBox<String> comboBox = new ComboBox<>();
                    comboBox.getItems().addAll("USER", "ADMIN");
                    comboBox.setValue("USER");

                    // Add to grid layout (label in column 0, input in column 1)
                    grid.add(label, 0, row);
                    grid.add(comboBox, 1, row);
                    GridPane.setHgrow(comboBox, Priority.ALWAYS);

                    // Store field/input pairs for later processing
                    fieldList.add(field);
                    inputList.add(comboBox);
                }

                // Special case for boolean fields → show CheckBox
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(true);

                    // Add to grid layout (label in column 0, input in column 1)
                    grid.add(label, 0, row);
                    grid.add(checkBox, 1, row);

                    // Store field/input pairs for later processing
                    fieldList.add(field);
                    inputList.add(checkBox);
                }

                // Default case → TextField
                else {

                    TextField input = new TextField();

                    // Add to grid layout (label in column 0, input in column 1)
                    grid.add(label, 0, row);
                    grid.add(input, 1, row);
                    GridPane.setHgrow(input, Priority.ALWAYS); // Make input fields expand horizontally

                    // Store field/input pairs for later processing
                    fieldList.add(field);
                    inputList.add(input);
                }

                row++;
            }

            // Set the grid as the dialog content
            dialog.getDialogPane().setContent(grid);

            // Handle "Add" button click - populate object with form data
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    try {
                        // Iterate through all field/input pairs and set values
                        for (int i = 0; i < fieldList.size(); i++) {
                            Field field = fieldList.get(i);
                            Control control = inputList.get(i);
                            Class<?> type = field.getType();

                            Object value = null;

                            if (control instanceof TextField tf) {
                                String text = tf.getText();

                                // Parse input based on field type using reflection
                                if (type == int.class || type == Integer.class) {
                                    value = Integer.parseInt(text);
                                } else if (type == double.class || type == Double.class) {
                                    value = Double.parseDouble(text);
                                } else {
                                    value = text; // Default to String
                                }
                            }
                            else if (control instanceof ComboBox<?> cb) {
                                value = cb.getValue();
                            }
                            else if (control instanceof CheckBox chk) {
                                value = chk.isSelected();
                            }

                            field.set(newObj, value);
                        }

                        return newObj; // Return populated object
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle parsing errors
                    }
                }
                return null; // Cancelled or error
            });

            // Show dialog and return result (blocks until user responds)
            return dialog.showAndWait().orElse(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle instantiation or reflection errors
        }
    }

    /**
     * Converts the first character of a string to uppercase for label display.
     * <p>Example: {@code "username"} → {@code "Username"}</p>
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}