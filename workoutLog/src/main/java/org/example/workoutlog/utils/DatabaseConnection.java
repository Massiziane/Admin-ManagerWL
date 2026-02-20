package org.example.workoutlog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = 
        "jdbc:postgresql://ep-morning-bonus-ahvqr3wz-pooler.c-3.us-east-1.aws.neon.tech/neondb"
        + "?user=neondb_owner&password=npg_7JNdAoH4Tefs&sslmode=require&channelBinding=require";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    // Test
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to NeonDB successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}