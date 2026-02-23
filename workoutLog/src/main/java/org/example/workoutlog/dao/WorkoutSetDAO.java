package org.example.workoutlog.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.WorkoutSet;
import org.example.workoutlog.service.DatabaseConnection;


public class WorkoutSetDAO {

    // 🔹 CREATE
    public void addWorkoutSet(WorkoutSet ws) {
        String sql = "INSERT INTO \"WorkoutSet\" (\"order\", setNumber, workoutExerciseId, setTemplateId, reps, weight, tempo, restTime) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (ws.getOrder() != null) stmt.setInt(1, ws.getOrder()); else stmt.setNull(1, Types.INTEGER);
            if (ws.getSetNumber() != null) stmt.setInt(2, ws.getSetNumber()); else stmt.setNull(2, Types.INTEGER);
            stmt.setInt(3, ws.getWorkoutExerciseId());
            if (ws.getSetTemplateId() != null) stmt.setInt(4, ws.getSetTemplateId()); else stmt.setNull(4, Types.INTEGER);
            if (ws.getReps() != null) stmt.setInt(5, ws.getReps()); else stmt.setNull(5, Types.INTEGER);
            if (ws.getWeight() != null) stmt.setDouble(6, ws.getWeight()); else stmt.setNull(6, Types.DOUBLE);
            stmt.setString(7, ws.getTempo());
            if (ws.getRestTime() != null) stmt.setInt(8, ws.getRestTime()); else stmt.setNull(8, Types.INTEGER);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 READ ALL
    public List<WorkoutSet> getAllWorkoutSets() {
        List<WorkoutSet> list = new ArrayList<>();
        String sql = "SELECT * FROM \"WorkoutSet\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new WorkoutSet(
                        rs.getInt("id"),
                        rs.getObject("order") != null ? rs.getInt("order") : null,
                        rs.getObject("setNumber") != null ? rs.getInt("setNumber") : null,
                        rs.getInt("workoutExerciseId"),
                        rs.getObject("setTemplateId") != null ? rs.getInt("setTemplateId") : null,
                        rs.getObject("reps") != null ? rs.getInt("reps") : null,
                        rs.getObject("weight") != null ? rs.getDouble("weight") : null,
                        rs.getString("tempo"),
                        rs.getObject("restTime") != null ? rs.getInt("restTime") : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 READ BY ID
    public WorkoutSet getWorkoutSetById(int id) {
        String sql = "SELECT * FROM \"WorkoutSet\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkoutSet(
                            rs.getInt("id"),
                            rs.getObject("order") != null ? rs.getInt("order") : null,
                            rs.getObject("setNumber") != null ? rs.getInt("setNumber") : null,
                            rs.getInt("workoutExerciseId"),
                            rs.getObject("setTemplateId") != null ? rs.getInt("setTemplateId") : null,
                            rs.getObject("reps") != null ? rs.getInt("reps") : null,
                            rs.getObject("weight") != null ? rs.getDouble("weight") : null,
                            rs.getString("tempo"),
                            rs.getObject("restTime") != null ? rs.getInt("restTime") : null
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 🔹 UPDATE
    public void updateWorkoutSet(WorkoutSet ws) {
        String sql = "UPDATE \"WorkoutSet\" SET \"order\" = ?, setNumber = ?, workoutExerciseId = ?, setTemplateId = ?, reps = ?, weight = ?, tempo = ?, restTime = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (ws.getOrder() != null) stmt.setInt(1, ws.getOrder()); else stmt.setNull(1, Types.INTEGER);
            if (ws.getSetNumber() != null) stmt.setInt(2, ws.getSetNumber()); else stmt.setNull(2, Types.INTEGER);
            stmt.setInt(3, ws.getWorkoutExerciseId());
            if (ws.getSetTemplateId() != null) stmt.setInt(4, ws.getSetTemplateId()); else stmt.setNull(4, Types.INTEGER);
            if (ws.getReps() != null) stmt.setInt(5, ws.getReps()); else stmt.setNull(5, Types.INTEGER);
            if (ws.getWeight() != null) stmt.setDouble(6, ws.getWeight()); else stmt.setNull(6, Types.DOUBLE);
            stmt.setString(7, ws.getTempo());
            if (ws.getRestTime() != null) stmt.setInt(8, ws.getRestTime()); else stmt.setNull(8, Types.INTEGER);
            stmt.setInt(9, ws.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    public void deleteWorkoutSet(int id) {
        String sql = "DELETE FROM \"WorkoutSet\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
