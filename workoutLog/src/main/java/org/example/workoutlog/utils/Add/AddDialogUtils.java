package org.example.workoutlog.utils.Add;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.lang.reflect.Field;

public class AddDialogUtils {

    /**
     * Opens a dialog to input values for all fields except "id".
     * Returns the filled object of type T.
     */
    public static <T> T addWithDialog(Class<T> clazz) {
        try {
            T newObj = clazz.getDeclaredConstructor().newInstance();

            Dialog<T> dialog = new Dialog<>();
            dialog.setTitle("Add " + clazz.getSimpleName());

            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            Field[] fields = clazz.getDeclaredFields();
            int row = 0;

            // store input fields alongside their Field objects
            java.util.List<Field> fieldList = new java.util.ArrayList<>();
            java.util.List<TextField> inputList = new java.util.ArrayList<>();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id")) continue; // skip only id

                Label label = new Label(capitalize(field.getName()) + ":");
                TextField input = new TextField();

                grid.add(label, 0, row);
                grid.add(input, 1, row);
                GridPane.setHgrow(input, Priority.ALWAYS);

                fieldList.add(field);
                inputList.add(input);
                row++;
            }

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    try {
                        for (int i = 0; i < fieldList.size(); i++) {
                            Field field = fieldList.get(i);
                            String text = inputList.get(i).getText();
                            Class<?> type = field.getType();

                            if (type == int.class || type == Integer.class) {
                                field.set(newObj, Integer.parseInt(text));
                            } else if (type == double.class || type == Double.class) {
                                field.set(newObj, Double.parseDouble(text));
                            } else if (type == boolean.class || type == Boolean.class) {
                                field.set(newObj, Boolean.parseBoolean(text));
                            } else {
                                field.set(newObj, text); // default to String
                            }
                        }
                        return newObj;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            });

            return dialog.showAndWait().orElse(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}