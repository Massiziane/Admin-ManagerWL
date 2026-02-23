package org.example.workoutlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.workoutlog.model.User;
import org.example.workoutlog.service.DatabaseConnection;

public class UserDAO {
    // Authenticate admin user
    public User authAdmin(String username, String password) {
       String sql = "SELECT * FROM \"User\" WHERE username = ? AND password = ? AND role = 'ADMIN' AND \"isActive\" = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("clerkId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getBoolean("isActive")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // no match
    }

     // CREATE
    public void addUser(User user) {
        String sql = """
            INSERT INTO "User" 
            (clerkId, firstName, lastName, username, email, role, isActive)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getClerkId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getRole());
            stmt.setBoolean(7, user.isActive());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  READ ALL
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM \"User\"";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("clerkId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getBoolean("isActive")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //  UPDATE
    public void updateUser(User user) {
        String sql = """
            UPDATE \"User\"
            SET firstName = ?, lastName = ?, username = ?, 
                email = ?, role = ?, isActive = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getRole());
            stmt.setBoolean(6, user.isActive());
            stmt.setInt(7, user.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUser(int id) {
        String sql = "DELETE FROM \"User\" WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
