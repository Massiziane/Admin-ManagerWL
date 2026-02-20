package org.example.workoutlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.Workout;
import org.example.workoutlog.utils.DatabaseConnection;

public class WorkoutDAO {

    // 🔹 CREATE
    public void addWorkout(Workout workout) {
        String sql = "INSERT INTO \"Workout\" (name, \"order\", frequency, userId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workout.getName());
            if (workout.getOrder() != null) {
                stmt.setInt(2, workout.getOrder());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (workout.getFrequency() != null) {
                stmt.setInt(3, workout.getFrequency());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, workout.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 READ ALL
    public List<Workout> getAllWorkouts() {
        List<Workout> list = new ArrayList<>();
        String sql = "SELECT * FROM \"Workout\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Workout(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getObject("order") != null ? rs.getInt("order") : null,
                        rs.getObject("frequency") != null ? rs.getInt("frequency") : null,
                        rs.getInt("userId")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 READ BY ID
    public Workout getWorkoutById(int id) {
        String sql = "SELECT * FROM \"Workout\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Workout(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getObject("order") != null ? rs.getInt("order") : null,
                            rs.getObject("frequency") != null ? rs.getInt("frequency") : null,
                            rs.getInt("userId")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found
    }

    // 🔹 UPDATE
    public void updateWorkout(Workout workout) {
        String sql = "UPDATE \"Workout\" SET name = ?, \"order\" = ?, frequency = ?, userId = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workout.getName());
            if (workout.getOrder() != null) {
                stmt.setInt(2, workout.getOrder());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (workout.getFrequency() != null) {
                stmt.setInt(3, workout.getFrequency());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, workout.getUserId());
            stmt.setInt(5, workout.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    public void deleteWorkout(int id) {
        String sql = "DELETE FROM \"Workout\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
