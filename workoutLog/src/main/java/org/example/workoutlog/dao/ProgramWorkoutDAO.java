package org.example.workoutlog.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.ProgramWorkout;
import org.example.workoutlog.utils.DatabaseConnection;
public class ProgramWorkoutDAO {

    // 🔹 CREATE
    public void addProgramWorkout(ProgramWorkout pw) {
        String sql = "INSERT INTO \"ProgramWorkout\" (programId, workoutId, \"order\") VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pw.getProgramId());
            stmt.setInt(2, pw.getWorkoutId());
            stmt.setInt(3, pw.getOrder());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 READ ALL
    public List<ProgramWorkout> getAllProgramWorkouts() {
        List<ProgramWorkout> list = new ArrayList<>();
        String sql = "SELECT * FROM \"ProgramWorkout\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ProgramWorkout(
                        rs.getInt("id"),
                        rs.getInt("programId"),
                        rs.getInt("workoutId"),
                        rs.getInt("order")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 READ BY ID
    public ProgramWorkout getProgramWorkoutById(int id) {
        String sql = "SELECT * FROM \"ProgramWorkout\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProgramWorkout(
                            rs.getInt("id"),
                            rs.getInt("programId"),
                            rs.getInt("workoutId"),
                            rs.getInt("order")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found
    }

    // 🔹 UPDATE
    public void updateProgramWorkout(ProgramWorkout pw) {
        String sql = "UPDATE \"ProgramWorkout\" SET programId = ?, workoutId = ?, \"order\" = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pw.getProgramId());
            stmt.setInt(2, pw.getWorkoutId());
            stmt.setInt(3, pw.getOrder());
            stmt.setInt(4, pw.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    public void deleteProgramWorkout(int id) {
        String sql = "DELETE FROM \"ProgramWorkout\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
