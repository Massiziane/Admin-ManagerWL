package org.example.workoutlog.utils.Edit;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.lang.reflect.Field;
import java.util.List;

public class EditDialogHelper {

    public static <T> GridPane createGridPane(T obj, List<Field> fieldList, List<TextField> inputList) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Field[] fields = obj.getClass().getDeclaredFields();
        int row = 0;

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase("id")) continue;

            Label label = new Label(capitalize(field.getName()) + ":");
            TextField input = new TextField();

            try {
                Object value = field.get(obj);
                input.setText(value != null ? value.toString() : "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            grid.add(label, 0, row);
            grid.add(input, 1, row);
            GridPane.setHgrow(input, Priority.ALWAYS);

            fieldList.add(field);
            inputList.add(input);
            row++;
        }

        return grid;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}