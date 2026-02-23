package org.example.workoutlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.Exercise;
import org.example.workoutlog.service.DatabaseConnection;


public class ExerciseDAO {

    // 🔹 CREATE
    public void addExercise(Exercise exercise) {
        String sql = "INSERT INTO \"Exercise\" (name, notes, categoryId, muscleGroupId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exercise.getName());
            stmt.setString(2, exercise.getNotes());
            if (exercise.getCategoryId() != null) {
                stmt.setInt(3, exercise.getCategoryId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            if (exercise.getMuscleGroupId() != null) {
                stmt.setInt(4, exercise.getMuscleGroupId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 READ ALL
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM \"Exercise\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                exercises.add(new Exercise(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("notes"),
                        rs.getObject("categoryId") != null ? rs.getInt("categoryId") : null,
                        rs.getObject("muscleGroupId") != null ? rs.getInt("muscleGroupId") : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercises;
    }

    // 🔹 READ BY ID
    public Exercise getExerciseById(int id) {
        String sql = "SELECT * FROM \"Exercise\" WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Exercise(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("notes"),
                            rs.getObject("categoryId") != null ? rs.getInt("categoryId") : null,
                            rs.getObject("muscleGroupId") != null ? rs.getInt("muscleGroupId") : null
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found
    }

    // 🔹 UPDATE
    public void updateExercise(Exercise exercise) {
        String sql = "UPDATE \"Exercise\" SET name = ?, notes = ?, categoryId = ?, muscleGroupId = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exercise.getName());
            stmt.setString(2, exercise.getNotes());
            if (exercise.getCategoryId() != null) {
                stmt.setInt(3, exercise.getCategoryId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            if (exercise.getMuscleGroupId() != null) {
                stmt.setInt(4, exercise.getMuscleGroupId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, exercise.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    public void deleteExercise(int id) {
        String sql = "DELETE FROM \"Exercise\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}