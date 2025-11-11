package com.cineman.dao;

import com.cineman.model.Customer;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Customer operations
 */
public class CustomerDAO {

    /**
     * Get customer by ID
     */
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getCustomerById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get customer by user ID
     */
    public Customer getCustomerByUserId(int userId) {
        String sql = "SELECT * FROM customers WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getCustomerByUserId: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get customer by phone number
     */
    public Customer getCustomerByPhone(String phone) {
        String sql = "SELECT * FROM customers WHERE phone = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getCustomerByPhone: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all customers
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(extractCustomerFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllCustomers: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Get all members
     */
    public List<Customer> getAllMembers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE is_member = TRUE ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(extractCustomerFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllMembers: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Insert new customer
     */
    public int insertCustomer(Customer customer) {
        String sql = "INSERT INTO customers (user_id, full_name, birth_date, address, email, phone, is_member) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setObject(1, customer.getUserId());
            stmt.setString(2, customer.getFullName());
            stmt.setDate(3, customer.getBirthDate());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getPhone());
            stmt.setBoolean(7, customer.isMember());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated ID
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error in insertCustomer: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Update customer
     */
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET user_id = ?, full_name = ?, birth_date = ?, address = ?, " +
                     "email = ?, phone = ?, is_member = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, customer.getUserId());
            stmt.setString(2, customer.getFullName());
            stmt.setDate(3, customer.getBirthDate());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getPhone());
            stmt.setBoolean(7, customer.isMember());
            stmt.setInt(8, customer.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateCustomer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update customer membership status
     */
    public boolean updateMembershipStatus(int customerId, boolean isMember) {
        String sql = "UPDATE customers SET is_member = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, isMember);
            stmt.setInt(2, customerId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateMembershipStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete customer
     */
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteCustomer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Search customers by name or phone
     */
    public List<Customer> searchCustomers(String keyword) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE full_name LIKE ? OR phone LIKE ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customers.add(extractCustomerFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in searchCustomers: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Extract Customer object from ResultSet
     */
    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setUserId((Integer) rs.getObject("user_id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setBirthDate(rs.getDate("birth_date"));
        customer.setAddress(rs.getString("address"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        customer.setMember(rs.getBoolean("is_member"));
        customer.setCreatedAt(rs.getTimestamp("created_at"));
        return customer;
    }
}
