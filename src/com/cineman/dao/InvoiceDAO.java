package com.cineman.dao;

import com.cineman.model.Invoice;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Invoice operations
 */
public class InvoiceDAO {

    /**
     * Get invoice by ID
     */
    public Invoice getInvoiceById(int id) {
        String sql = "SELECT * FROM invoices WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractInvoiceFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getInvoiceById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get invoice by invoice number
     */
    public Invoice getInvoiceByNumber(String invoiceNumber) {
        String sql = "SELECT * FROM invoices WHERE invoice_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, invoiceNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractInvoiceFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getInvoiceByNumber: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all invoices
     */
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                invoices.add(extractInvoiceFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllInvoices: " + e.getMessage());
            e.printStackTrace();
        }

        return invoices;
    }

    /**
     * Get invoices by customer ID
     */
    public List<Invoice> getInvoicesByCustomerId(int customerId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices WHERE customer_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                invoices.add(extractInvoiceFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getInvoicesByCustomerId: " + e.getMessage());
            e.printStackTrace();
        }

        return invoices;
    }

    /**
     * Get invoices by showtime ID
     */
    public List<Invoice> getInvoicesByShowtimeId(int showtimeId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices WHERE showtime_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                invoices.add(extractInvoiceFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getInvoicesByShowtimeId: " + e.getMessage());
            e.printStackTrace();
        }

        return invoices;
    }

    /**
     * Get invoices by date range
     */
    public List<Invoice> getInvoicesByDateRange(Date startDate, Date endDate) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices WHERE DATE(created_at) BETWEEN ? AND ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                invoices.add(extractInvoiceFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getInvoicesByDateRange: " + e.getMessage());
            e.printStackTrace();
        }

        return invoices;
    }

    /**
     * Insert new invoice and return generated ID
     */
    public int insertInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, " +
                     "total_amount, discount, final_amount, payment_method, status, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, invoice.getInvoiceNumber());
            stmt.setObject(2, invoice.getCustomerId());
            stmt.setObject(3, invoice.getStaffId());
            stmt.setInt(4, invoice.getShowtimeId());
            stmt.setDouble(5, invoice.getTotalAmount());
            stmt.setDouble(6, invoice.getDiscount());
            stmt.setDouble(7, invoice.getFinalAmount());
            stmt.setString(8, invoice.getPaymentMethod());
            stmt.setString(9, invoice.getStatus());
            stmt.setString(10, invoice.getNotes());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error in insertInvoice: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Update invoice
     */
    public boolean updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET invoice_number = ?, customer_id = ?, staff_id = ?, showtime_id = ?, " +
                     "total_amount = ?, discount = ?, final_amount = ?, payment_method = ?, status = ?, notes = ? " +
                     "WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, invoice.getInvoiceNumber());
            stmt.setObject(2, invoice.getCustomerId());
            stmt.setObject(3, invoice.getStaffId());
            stmt.setInt(4, invoice.getShowtimeId());
            stmt.setDouble(5, invoice.getTotalAmount());
            stmt.setDouble(6, invoice.getDiscount());
            stmt.setDouble(7, invoice.getFinalAmount());
            stmt.setString(8, invoice.getPaymentMethod());
            stmt.setString(9, invoice.getStatus());
            stmt.setString(10, invoice.getNotes());
            stmt.setInt(11, invoice.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateInvoice: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update invoice status
     */
    public boolean updateInvoiceStatus(int invoiceId, String status) {
        String sql = "UPDATE invoices SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, invoiceId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateInvoiceStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete invoice
     */
    public boolean deleteInvoice(int id) {
        String sql = "DELETE FROM invoices WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteInvoice: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Generate next invoice number
     */
    public String generateInvoiceNumber() {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_generate_invoice_number(?)}")) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            return stmt.getString(1);

        } catch (SQLException e) {
            System.err.println("Error in generateInvoiceNumber: " + e.getMessage());
            e.printStackTrace();
            // Fallback: generate manually
            return generateInvoiceNumberManually();
        }
    }

    /**
     * Fallback method to generate invoice number manually
     */
    private String generateInvoiceNumberManually() {
        String sql = "SELECT MAX(CAST(SUBSTRING(invoice_number, 11) AS UNSIGNED)) as max_num " +
                     "FROM invoices WHERE invoice_number LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(new java.util.Date());
            String datePrefix = "INV" + currentDate + "%";
            stmt.setString(1, datePrefix);

            ResultSet rs = stmt.executeQuery();
            int nextNum = 1;

            if (rs.next()) {
                nextNum = rs.getInt("max_num") + 1;
            }

            return String.format("INV%s%04d", currentDate, nextNum);

        } catch (SQLException e) {
            System.err.println("Error in generateInvoiceNumberManually: " + e.getMessage());
            e.printStackTrace();
            // Last resort: use timestamp
            return "INV" + System.currentTimeMillis();
        }
    }

    /**
     * Get total revenue by date range
     */
    public double getTotalRevenue(Date startDate, Date endDate) {
        String sql = "SELECT SUM(final_amount) as total FROM invoices " +
                     "WHERE DATE(created_at) BETWEEN ? AND ? AND status = 'PAID'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.err.println("Error in getTotalRevenue: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.0;
    }

    /**
     * Extract Invoice object from ResultSet
     */
    private Invoice extractInvoiceFromResultSet(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("id"));
        invoice.setInvoiceNumber(rs.getString("invoice_number"));
        invoice.setCustomerId((Integer) rs.getObject("customer_id"));
        invoice.setStaffId((Integer) rs.getObject("staff_id"));
        invoice.setShowtimeId(rs.getInt("showtime_id"));
        invoice.setCreatedAt(rs.getTimestamp("created_at"));
        invoice.setTotalAmount(rs.getDouble("total_amount"));
        invoice.setDiscount(rs.getDouble("discount"));
        invoice.setFinalAmount(rs.getDouble("final_amount"));
        invoice.setPaymentMethod(rs.getString("payment_method"));
        invoice.setStatus(rs.getString("status"));
        invoice.setNotes(rs.getString("notes"));
        return invoice;
    }
}
