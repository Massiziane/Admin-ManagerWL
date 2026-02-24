package org.example.workoutlog.utils.Delete;

import javafx.scene.control.TableView;

public class DeleteUtils {

    /**
     * Deletes an object using its DAO and refreshes the TableView.
     * @param dao DAO instance (must have deleteX(int id) and getAllX() methods)
     * @param obj Object to delete (must have getId() method)
     * @param tableView TableView to refresh (can be null)
     * @param <T> Model type
     */
    public static <T> void delete(Object dao, T obj, TableView<Object> tableView) {
        if (obj == null || dao == null) return;

        try {
            // Get ID via reflection
            int id = (int) obj.getClass().getMethod("getId").invoke(obj);

            // Call delete method on DAO reflectively
            String deleteMethodName = "delete" + obj.getClass().getSimpleName();
            dao.getClass().getMethod(deleteMethodName, int.class).invoke(dao, id);

            // Refresh TableView if provided
            if (tableView != null) {
                String getAllMethodName = "getAll" + obj.getClass().getSimpleName() + "s";
                Object data = dao.getClass().getMethod(getAllMethodName).invoke(dao);
                if (data instanceof java.util.List<?> list) {
                    tableView.getItems().setAll(list);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}