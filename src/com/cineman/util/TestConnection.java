package com.cineman.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Test class to verify database connection
 * Run this to check if MySQL is working
 */
public class TestConnection {

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("Testing CineMan Database Connection");
        System.out.println("===========================================");

        // Test 1: Basic connection
        System.out.println("\n[Test 1] Testing basic connection...");
        if (DBConnection.testConnection()) {
            System.out.println("✅ SUCCESS: Database connected!");
        } else {
            System.out.println("❌ FAILED: Cannot connect to database");
            System.out.println("Check if MySQL Docker is running: docker ps");
            return;
        }

        // Test 2: Query data
        System.out.println("\n[Test 2] Testing data query...");
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Count users
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
            if (rs.next()) {
                int userCount = rs.getInt("count");
                System.out.println("✅ SUCCESS: Found " + userCount + " users in database");
            }

            // Show sample users
            rs = stmt.executeQuery("SELECT username, role, full_name FROM users LIMIT 3");
            System.out.println("\nSample users:");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s | %-10s | %s%n",
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getString("full_name"));
            }
            System.out.println("----------------------------------------");

            // Count movies
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM movies");
            if (rs.next()) {
                int movieCount = rs.getInt("count");
                System.out.println("\n✅ SUCCESS: Found " + movieCount + " movies in database");
            }

            System.out.println("\n===========================================");
            System.out.println("All tests passed! Database is ready.");
            System.out.println("===========================================");

        } catch (Exception e) {
            System.out.println("❌ FAILED: Error querying database");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
