package org.example.workoutlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.Category;
import org.example.workoutlog.service.DatabaseConnection;

/**
 * Data Access Object (DAO) for managing {@link Category} records in the database.
 *
 * This class handles CRUD operations (Create, Read, Update, Delete)
 * for the { Category} table using JDBC. It also ensures that when
 * a category is deleted, all exercises linked to it are reassigned
 * to a fallback category.
 */
public class CategoryDAO {

    /**
     * Retrieves all categories from the database.
     */
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM \"Category\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Map each row in the ResultSet to a Category object
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("Desc")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Inserts a new category into the database.
     *
     * @param category The {@link Category} object to add.
     */
    public void addCategory(Category category) {
        String sql = "INSERT INTO \"Category\" (name, \"Desc\") VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDesc());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing category’s name and description in the database.
     */
    public void updateCategory(Category category) {
        String sql = "UPDATE \"Category\" SET name = ?, \"Desc\" = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDesc());
            stmt.setInt(3, category.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


     // Delete
    public void deleteCategory(int id) {
        // Set the fallback category ID (the one named "Exercises")
        final int fallbackCategoryId = 28; // ID of default/fallback category

        String updateSql = "UPDATE \"Exercise\" SET \"categoryId\" = ? WHERE \"categoryId\" = ?";
        String deleteSql = "DELETE FROM \"Category\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {

            // Step 1: Reassign exercises to the fallback category
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, fallbackCategoryId);
                updateStmt.setInt(2, id);
                updateStmt.executeUpdate();
            }

            // Step 2: Delete the category after reassignment
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
