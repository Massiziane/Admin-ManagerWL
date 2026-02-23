package org.example.workoutlog.utils;

import java.lang.reflect.Field;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableUtils {

    public static void loadTable(TableView<Object> tableView, List<?> items) {
        // Clear previous data and columns
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // Add CSS class for styling
        if (!tableView.getStyleClass().contains("dynamic-table")) {
            tableView.getStyleClass().add("dynamic-table");
        }

        if (items.isEmpty()) return;

        Object sample = items.get(0);

        // Create columns dynamically
        for (Field field : sample.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            TableColumn<Object, Object> column = new TableColumn<>(capitalize(field.getName()));

            // Cell value via reflection
            column.setCellValueFactory(cellData -> {
                try {
                    return new javafx.beans.property.SimpleObjectProperty<>(field.get(cellData.getValue()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            });

            // Remove fixed width to allow CONSTRAINED_RESIZE_POLICY to work properly
            column.setMinWidth(50);      // minimum so it doesn't collapse
            column.setPrefWidth(150);    // optional, initial sizing
            column.setResizable(true);   // allow resizing

            tableView.getColumns().add(column);
        }

        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        // After adding data, apply a simple hack to evenly distribute widths
        ObservableList<Object> data = FXCollections.observableArrayList(items);
        tableView.setItems(data);

        // Force equal width for all columns
        tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
            int colCount = tableView.getColumns().size();
            if (colCount == 0) return;

            double width = newVal.doubleValue() / colCount;
            for (TableColumn<Object, ?> col : tableView.getColumns()) {
                col.setPrefWidth(width);
            }
        });
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}