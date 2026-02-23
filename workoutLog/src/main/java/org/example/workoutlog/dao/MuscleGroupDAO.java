package org.example.workoutlog.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.MuscleGroup;
import org.example.workoutlog.service.DatabaseConnection;

public class MuscleGroupDAO {

    //  CREATE
    public void addMuscleGroup(MuscleGroup muscleGroup) {
        String sql = "INSERT INTO \"MuscleGroup\" (name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, muscleGroup.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  READ ALL
    public List<MuscleGroup> getAllMuscleGroups() {
        List<MuscleGroup> list = new ArrayList<>();
        String sql = "SELECT * FROM \"MuscleGroup\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new MuscleGroup(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //  READ BY ID
    public MuscleGroup getMuscleGroupById(int id) {
        String sql = "SELECT * FROM \"MuscleGroup\" WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MuscleGroup(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Not found
    }

    //  UPDATE
    public void updateMuscleGroup(MuscleGroup muscleGroup) {
        String sql = "UPDATE \"MuscleGroup\" SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, muscleGroup.getName());
            stmt.setInt(2, muscleGroup.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  DELETE
    public void deleteMuscleGroup(int id) {
        String sql = "DELETE FROM \"MuscleGroup\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
