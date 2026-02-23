package org.example.workoutlog.utils;

import java.lang.reflect.Field;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableUtils {

    /**
     * Dynamically loads a list of objects into a TableView.
     * Shows a placeholder label if the list is empty.
     *
     * @param tableView The TableView to populate
     * @param items     The list of objects to display
     */
    public static void loadTable(TableView<Object> tableView, List<?> items) {
        // Clear previous data and columns
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // Add a CSS class for styling dynamic tables
        if (!tableView.getStyleClass().contains("dynamic-table")) {
            tableView.getStyleClass().add("dynamic-table");
        }

        if (items.isEmpty()) {
            Label placeholder = new Label("No data in this table.");
            placeholder.setStyle("-fx-text-fill: #333333; -fx-font-size: 16px; -fx-font-weight: bold;");
            placeholder.setMaxWidth(Double.MAX_VALUE);
            placeholder.setWrapText(true);
            placeholder.setAlignment(javafx.geometry.Pos.CENTER);
            tableView.setPlaceholder(placeholder);
            return;
        }
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

            column.setMinWidth(50);
            column.setPrefWidth(150);
            column.setResizable(true);

            tableView.getColumns().add(column);
        }

        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        ObservableList<Object> data = FXCollections.observableArrayList(items);
        tableView.setItems(data);

        // Force even column widths
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