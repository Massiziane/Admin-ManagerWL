package org.example.workoutlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.SetTemplate;
import org.example.workoutlog.service.DatabaseConnection;

public class SetTemplateDAO {

    // CREATE
    public void addSetTemplate(SetTemplate st) {
        String sql = "INSERT INTO \"SetTemplate\" (reps, weight, tempo, \"restTime\") VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (st.getReps() != null) stmt.setInt(1, st.getReps()); else stmt.setNull(1, Types.INTEGER);
            if (st.getWeight() != null) stmt.setDouble(2, st.getWeight()); else stmt.setNull(2, Types.DOUBLE);
            stmt.setString(3, st.getTempo());
            if (st.getRestTime() != null) stmt.setInt(4, st.getRestTime()); else stmt.setNull(4, Types.INTEGER);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<SetTemplate> getAllSetTemplates() {
        List<SetTemplate> list = new ArrayList<>();
        String sql = "SELECT * FROM \"SetTemplate\" ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new SetTemplate(
                        rs.getInt("id"),
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

    // READ BY ID
    public SetTemplate getSetTemplateById(int id) {
        String sql = "SELECT * FROM \"SetTemplate\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SetTemplate(
                            rs.getInt("id"),
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

    // UPDATE
    public void updateSetTemplate(SetTemplate st) {
        String sql = "UPDATE \"SetTemplate\" SET reps = ?, weight = ?, tempo = ?, \"restTime\" = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (st.getReps() != null) stmt.setInt(1, st.getReps()); else stmt.setNull(1, Types.INTEGER);
            if (st.getWeight() != null) stmt.setDouble(2, st.getWeight()); else stmt.setNull(2, Types.DOUBLE);
            stmt.setString(3, st.getTempo());
            if (st.getRestTime() != null) stmt.setInt(4, st.getRestTime()); else stmt.setNull(4, Types.INTEGER);
            stmt.setInt(5, st.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteSetTemplate(int id) {
        String sql = "DELETE FROM \"SetTemplate\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}