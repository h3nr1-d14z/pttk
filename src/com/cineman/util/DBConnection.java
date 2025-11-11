package com.cineman.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class để quản lý kết nối database
 * Sử dụng pattern Singleton để đảm bảo chỉ có 1 instance
 */
public class DBConnection {
    // Thông tin kết nối MySQL (Docker)
    private static final String URL = "jdbc:mysql://localhost:3306/cineman?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "cineman_user";
    private static final String PASSWORD = "cineman123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Lấy kết nối đến database
     * @return Connection object
     * @throws SQLException nếu không kết nối được
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver không tìm thấy! Hãy thêm mysql-connector-java.jar vào lib", e);
        }
    }

    /**
     * Đóng kết nối database
     * @param conn Connection cần đóng
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng connection: " + e.getMessage());
            }
        }
    }

    /**
     * Test kết nối database
     * @return true nếu kết nối thành công, false nếu thất bại
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
            return false;
        }
    }
}
