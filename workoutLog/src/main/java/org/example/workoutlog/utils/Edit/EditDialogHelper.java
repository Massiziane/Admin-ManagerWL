package org.example.workoutlog.utils.Edit;

import java.lang.reflect.Field;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class EditDialogHelper {

    /**
     * Dynamically creates a GridPane containing labels and text fields for editing an object's fields.
     * The grid can then be used in a dialog for editing an object.
     *
     * @param obj        The object to edit
     * @param fieldList  List to store the reflected Fields (so we can update the object later)
     * @param inputList  List to store the TextField inputs (to get the new values after editing)
     * @param <T>        The type of the object being edited
     * @return GridPane populated with Labels and TextFields for each editable field
     */
    public static <T> GridPane createGridPane(T obj, List<Field> fieldList, List<TextField> inputList) {
        // Create a new grid for the form
        GridPane grid = new GridPane();
        grid.setHgap(10); // horizontal spacing between columns
        grid.setVgap(10); // vertical spacing between rows

        Field[] fields = obj.getClass().getDeclaredFields(); // get all declared fields of the object
        int row = 0;

        for (Field field : fields) {
            field.setAccessible(true); // allow access to private fields
            if (field.getName().equalsIgnoreCase("id")) continue; // skip ID field (not editable)

            // Create a label for the field
            Label label = new Label(capitalize(field.getName()) + ":");

            // Create a text field pre-populated with the current value
            TextField input = new TextField();
            try {
                Object value = field.get(obj); // get current field value
                input.setText(value != null ? value.toString() : ""); // display empty string if null
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // should not happen due to setAccessible(true)
            }

            // Add the label and input to the grid
            grid.add(label, 0, row); // label in first column
            grid.add(input, 1, row); // input in second column
            GridPane.setHgrow(input, Priority.ALWAYS); // allow input to expand horizontally

            // Store references for later use (e.g., updating the object)
            fieldList.add(field);
            inputList.add(input);

            row++; // move to next row
        }

        return grid;
    }

    /**
     * Capitalizes the first letter of a string.
     *
     * @param str The input string
     * @return String with first character capitalized
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}