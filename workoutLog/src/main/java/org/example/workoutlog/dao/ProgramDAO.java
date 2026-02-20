package org.example.workoutlog.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.Program;
import org.example.workoutlog.utils.DatabaseConnection;

public class ProgramDAO {

    // 🔹 CREATE
    public void addProgram(Program program) {
        String sql = "INSERT INTO \"Program\" (name, Desc, userId) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, program.getName());
            stmt.setString(2, program.getDesc());
            stmt.setInt(3, program.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 READ ALL
    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String sql = "SELECT * FROM \"Program\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                programs.add(new Program(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("Desc"),
                        rs.getInt("userId")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programs;
    }

    // 🔹 READ BY ID
    public Program getProgramById(int id) {
        String sql = "SELECT * FROM \"Program\" WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Program(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("Desc"),
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
    public void updateProgram(Program program) {
        String sql = "UPDATE \"Program\" SET name = ?, Desc = ?, userId = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, program.getName());
            stmt.setString(2, program.getDesc());
            stmt.setInt(3, program.getUserId());
            stmt.setInt(4, program.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    public void deleteProgram(int id) {
        String sql = "DELETE FROM \"Program\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
