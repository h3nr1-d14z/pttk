package com.cineman.dao;

import com.cineman.model.Membership;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Membership operations
 */
public class MembershipDAO {

    /**
     * Get membership by ID
     */
    public Membership getMembershipById(int id) {
        String sql = "SELECT * FROM memberships WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractMembershipFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getMembershipById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get membership by customer ID
     */
    public Membership getMembershipByCustomerId(int customerId) {
        String sql = "SELECT * FROM memberships WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractMembershipFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getMembershipByCustomerId: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get membership by card number
     */
    public Membership getMembershipByCardNumber(String cardNumber) {
        String sql = "SELECT * FROM memberships WHERE card_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractMembershipFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getMembershipByCardNumber: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all memberships
     */
    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                memberships.add(extractMembershipFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllMemberships: " + e.getMessage());
            e.printStackTrace();
        }

        return memberships;
    }

    /**
     * Get active memberships
     */
    public List<Membership> getActiveMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE status = 'ACTIVE' ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                memberships.add(extractMembershipFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getActiveMemberships: " + e.getMessage());
            e.printStackTrace();
        }

        return memberships;
    }

    /**
     * Insert new membership
     */
    public boolean insertMembership(Membership membership) {
        String sql = "INSERT INTO memberships (customer_id, card_number, registration_date, expiry_date, " +
                     "points, card_type, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membership.getCustomerId());
            stmt.setString(2, membership.getCardNumber());
            stmt.setDate(3, membership.getRegistrationDate());
            stmt.setDate(4, membership.getExpiryDate());
            stmt.setInt(5, membership.getPoints());
            stmt.setString(6, membership.getCardType());
            stmt.setString(7, membership.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in insertMembership: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update membership
     */
    public boolean updateMembership(Membership membership) {
        String sql = "UPDATE memberships SET customer_id = ?, card_number = ?, registration_date = ?, " +
                     "expiry_date = ?, points = ?, card_type = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membership.getCustomerId());
            stmt.setString(2, membership.getCardNumber());
            stmt.setDate(3, membership.getRegistrationDate());
            stmt.setDate(4, membership.getExpiryDate());
            stmt.setInt(5, membership.getPoints());
            stmt.setString(6, membership.getCardType());
            stmt.setString(7, membership.getStatus());
            stmt.setInt(8, membership.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateMembership: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update membership points
     */
    public boolean updatePoints(int membershipId, int points) {
        String sql = "UPDATE memberships SET points = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, points);
            stmt.setInt(2, membershipId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updatePoints: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add points to membership
     */
    public boolean addPoints(int membershipId, int pointsToAdd) {
        String sql = "UPDATE memberships SET points = points + ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pointsToAdd);
            stmt.setInt(2, membershipId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in addPoints: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update membership status
     */
    public boolean updateStatus(int membershipId, String status) {
        String sql = "UPDATE memberships SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, membershipId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete membership
     */
    public boolean deleteMembership(int id) {
        String sql = "DELETE FROM memberships WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteMembership: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Generate next card number
     */
    public String generateCardNumber() {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_generate_card_number(?)}")) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            return stmt.getString(1);

        } catch (SQLException e) {
            System.err.println("Error in generateCardNumber: " + e.getMessage());
            e.printStackTrace();
            // Fallback: generate manually if stored procedure fails
            return generateCardNumberManually();
        }
    }

    /**
     * Fallback method to generate card number manually
     */
    private String generateCardNumberManually() {
        String sql = "SELECT MAX(CAST(SUBSTRING(card_number, 8) AS UNSIGNED)) as max_num " +
                     "FROM memberships WHERE card_number LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            String yearPrefix = "MEM" + currentYear + "%";
            stmt.setString(1, yearPrefix);

            ResultSet rs = stmt.executeQuery();
            int nextNum = 1;

            if (rs.next()) {
                nextNum = rs.getInt("max_num") + 1;
            }

            return String.format("MEM%d%03d", currentYear, nextNum);

        } catch (SQLException e) {
            System.err.println("Error in generateCardNumberManually: " + e.getMessage());
            e.printStackTrace();
            // Last resort: use timestamp
            return "MEM" + System.currentTimeMillis();
        }
    }

    /**
     * Extract Membership object from ResultSet
     */
    private Membership extractMembershipFromResultSet(ResultSet rs) throws SQLException {
        Membership membership = new Membership();
        membership.setId(rs.getInt("id"));
        membership.setCustomerId(rs.getInt("customer_id"));
        membership.setCardNumber(rs.getString("card_number"));
        membership.setRegistrationDate(rs.getDate("registration_date"));
        membership.setExpiryDate(rs.getDate("expiry_date"));
        membership.setPoints(rs.getInt("points"));
        membership.setCardType(rs.getString("card_type"));
        membership.setStatus(rs.getString("status"));
        return membership;
    }
}
