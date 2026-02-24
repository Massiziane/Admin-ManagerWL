package org.example.workoutlog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 * Provides a centralized method to get a JDBC connection to the PostgreSQL database.
 */
public class DatabaseConnection {

    // JDBC URL for connecting to the Neon PostgreSQL database.
    // Includes user credentials, SSL mode, and channel binding.
    private static final String JDBC_URL = 
        "jdbc:postgresql://ep-morning-bonus-ahvqr3wz-pooler.c-3.us-east-1.aws.neon.tech/neondb"
        + "?user=neondb_owner&password=npg_7JNdAoH4Tefs&sslmode=require&channelBinding=require";

    /**
     * Returns a new Connection object to the database.
     * Each call returns a fresh connection, which should be closed after use.
     *
     * @return Connection to the Neon PostgreSQL database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    /**
     * Simple test method to verify that the database connection works.
     * Prints a success message if the connection is established.
     */
    public static void main(String[] args) {
        try (Connection conn = getConnection()) { // Try-with-resources ensures connection is closed automatically
            if (conn != null) {
                System.out.println("Connected to NeonDB successfully!");
            }
        } catch (SQLException e) {
            // Print full stack trace for debugging connection errors
            e.printStackTrace();
        }
    }
}