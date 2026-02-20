package org.example.workoutlog.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.List;

public class TableUtils {

    public static void loadTable(TableView<Object> tableView, List<?> items) {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        if (items.isEmpty()) return;

        Object sample = items.get(0);

        for (Field field : sample.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            TableColumn<Object, Object> column = new TableColumn<>(capitalize(field.getName()));
            column.setCellValueFactory(cellData -> {
                try {
                    return new javafx.beans.property.SimpleObjectProperty<>(field.get(cellData.getValue()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            });
            tableView.getColumns().add(column);
        }

        ObservableList<Object> data = FXCollections.observableArrayList(items);
        tableView.setItems(data);
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}