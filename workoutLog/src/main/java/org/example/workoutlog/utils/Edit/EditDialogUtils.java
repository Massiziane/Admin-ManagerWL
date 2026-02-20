package org.example.workoutlog.utils.Edit;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EditDialogUtils {

    /**
     * Opens a dialog to edit all fields (except "id") of the given object.
     * Returns the edited object.
     */
    public static <T> T editWithDialog(T obj) {
        try {
            Dialog<T> dialog = new Dialog<>();
            dialog.setTitle("Edit " + obj.getClass().getSimpleName());

            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            List<Field> fieldList = new ArrayList<>();
            List<TextField> inputList = new ArrayList<>();
            dialog.getDialogPane().setContent(EditDialogHelper.createGridPane(obj, fieldList, inputList));

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    try {
                        for (int i = 0; i < fieldList.size(); i++) {
                            Field field = fieldList.get(i);
                            String text = inputList.get(i).getText();
                            Class<?> type = field.getType();

                            if (type == int.class || type == Integer.class) {
                                field.set(obj, Integer.parseInt(text));
                            } else if (type == double.class || type == Double.class) {
                                field.set(obj, Double.parseDouble(text));
                            } else if (type == boolean.class || type == Boolean.class) {
                                field.set(obj, Boolean.parseBoolean(text));
                            } else {
                                field.set(obj, text);
                            }
                        }
                        return obj;
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
}