package org.example.workoutlog.utils.Edit;

import java.lang.reflect.Field;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class EditDialogHelper {

    /**
     * Dynamically creates a GridPane containing labels and text fields for editing an object's fields.
     * The grid can then be used in a dialog for editing an object.
     */
    public static <T> GridPane createGridPane(T obj, List<Field> fieldList, List<Control> inputList) {
        
        GridPane grid = new GridPane();
        grid.setHgap(10); 
        grid.setVgap(10);

        Field[] fields = obj.getClass().getDeclaredFields(); // get all declared fields of the object
        int row = 0;

        for (Field field : fields) {
            field.setAccessible(true); // allow access to private fields
            if (field.getName().equalsIgnoreCase("id")) continue; // skip ID field (not editable)

            // Create a label for the field
            Label label = new Label(capitalize(field.getName()) + ":");

            try {
                Object value = field.get(obj);

                // Special case for "role" field → ComboBox
                if (field.getName().equalsIgnoreCase("role")) {

                    ComboBox<String> comboBox = new ComboBox<>();
                    comboBox.getItems().addAll("USER", "ADMIN");
                    comboBox.setValue(value != null ? value.toString() : "USER");

                    // Add the label and input to the grid
                    grid.add(label, 0, row);
                    grid.add(comboBox, 1, row);
                    GridPane.setHgrow(comboBox, Priority.ALWAYS);

                    // Store references for later use (e.g., updating the object)
                    fieldList.add(field);
                    inputList.add(comboBox);
                }

                // Special case for boolean fields → CheckBox
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(value != null && (Boolean) value);

                    // Add the label and input to the grid
                    grid.add(label, 0, row);
                    grid.add(checkBox, 1, row);

                    // Store references for later use (e.g., updating the object)
                    fieldList.add(field);
                    inputList.add(checkBox);
                }

                // Default case → TextField
                else {

                    // Create a text field pre-populated with the current value
                    TextField input = new TextField();
                    input.setText(value != null ? value.toString() : "");

                    // Add the label and input to the grid
                    grid.add(label, 0, row); // label in first column
                    grid.add(input, 1, row); // input in second column
                    GridPane.setHgrow(input, Priority.ALWAYS); // allow input to expand horizontally

                    // Store references for later use (e.g., updating the object)
                    fieldList.add(field);
                    inputList.add(input);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace(); 
            }

            row++; // move to next row
        }

        return grid;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}