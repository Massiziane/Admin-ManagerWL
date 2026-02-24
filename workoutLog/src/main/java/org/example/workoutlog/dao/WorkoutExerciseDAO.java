package org.example.workoutlog.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.WorkoutExercise;
import org.example.workoutlog.service.DatabaseConnection;

public class WorkoutExerciseDAO {

    // CREATE
    public void addWorkoutExercise(WorkoutExercise we) {
        String sql = "INSERT INTO \"WorkoutExercise\" (\"order\", notes, workoutId, exerciseId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (we.getOrder() != null) {
                stmt.setInt(1, we.getOrder());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setString(2, we.getNotes());
            stmt.setInt(3, we.getWorkoutId());
            stmt.setInt(4, we.getExerciseId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<WorkoutExercise> getAllWorkoutExercises() {
        List<WorkoutExercise> list = new ArrayList<>();
        String sql = "SELECT * FROM \"WorkoutExercise\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new WorkoutExercise(
                        rs.getInt("id"),
                        rs.getObject("order") != null ? rs.getInt("order") : null,
                        rs.getString("notes"),
                        rs.getInt("workoutId"),
                        rs.getInt("exerciseId")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // READ BY ID
    public WorkoutExercise getWorkoutExerciseById(int id) {
        String sql = "SELECT * FROM \"WorkoutExercise\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkoutExercise(
                            rs.getInt("id"),
                            rs.getObject("order") != null ? rs.getInt("order") : null,
                            rs.getString("notes"),
                            rs.getInt("workoutId"),
                            rs.getInt("exerciseId")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found
    }

    // UPDATE
    public void updateWorkoutExercise(WorkoutExercise we) {
        String sql = "UPDATE \"WorkoutExercise\" SET \"order\" = ?, notes = ?, workoutId = ?, exerciseId = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (we.getOrder() != null) {
                stmt.setInt(1, we.getOrder());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setString(2, we.getNotes());
            stmt.setInt(3, we.getWorkoutId());
            stmt.setInt(4, we.getExerciseId());
            stmt.setInt(5, we.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteWorkoutExercise(int id) {
        String sql = "DELETE FROM \"WorkoutExercise\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
}
