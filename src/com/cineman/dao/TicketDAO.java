package com.cineman.dao;

import com.cineman.model.Ticket;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Ticket operations
 */
public class TicketDAO {

    /**
     * Get ticket by ID
     */
    public Ticket getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractTicketFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getTicketById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all tickets
     */
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tickets.add(extractTicketFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllTickets: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    /**
     * Get tickets by invoice ID
     */
    public List<Ticket> getTicketsByInvoiceId(int invoiceId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE invoice_id = ? ORDER BY seat_number";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(extractTicketFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getTicketsByInvoiceId: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    /**
     * Get tickets by showtime ID
     */
    public List<Ticket> getTicketsByShowtimeId(int showtimeId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE showtime_id = ? ORDER BY seat_number";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(extractTicketFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getTicketsByShowtimeId: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    /**
     * Get sold tickets by showtime ID
     */
    public List<Ticket> getSoldTicketsByShowtimeId(int showtimeId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE showtime_id = ? AND status = 'SOLD' ORDER BY seat_number";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(extractTicketFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getSoldTicketsByShowtimeId: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    /**
     * Get occupied seats for a showtime
     */
    public List<String> getOccupiedSeats(int showtimeId) {
        List<String> seats = new ArrayList<>();
        String sql = "SELECT seat_number FROM tickets WHERE showtime_id = ? AND status IN ('BOOKED', 'SOLD')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                seats.add(rs.getString("seat_number"));
            }

        } catch (SQLException e) {
            System.err.println("Error in getOccupiedSeats: " + e.getMessage());
            e.printStackTrace();
        }

        return seats;
    }

    /**
     * Check if seat is available
     */
    public boolean isSeatAvailable(int showtimeId, String seatNumber) {
        String sql = "SELECT COUNT(*) FROM tickets WHERE showtime_id = ? AND seat_number = ? " +
                     "AND status IN ('BOOKED', 'SOLD')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            stmt.setString(2, seatNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            System.err.println("Error in isSeatAvailable: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Insert new ticket
     */
    public boolean insertTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getInvoiceId());
            stmt.setInt(2, ticket.getShowtimeId());
            stmt.setString(3, ticket.getSeatNumber());
            stmt.setDouble(4, ticket.getTicketPrice());
            stmt.setString(5, ticket.getSeatType());
            stmt.setString(6, ticket.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in insertTicket: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Insert multiple tickets in a transaction
     */
    public boolean insertTickets(List<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql);

            for (Ticket ticket : tickets) {
                stmt.setInt(1, ticket.getInvoiceId());
                stmt.setInt(2, ticket.getShowtimeId());
                stmt.setString(3, ticket.getSeatNumber());
                stmt.setDouble(4, ticket.getTicketPrice());
                stmt.setString(5, ticket.getSeatType());
                stmt.setString(6, ticket.getStatus());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error in insertTickets: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error rolling back transaction: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    /**
     * Update ticket
     */
    public boolean updateTicket(Ticket ticket) {
        String sql = "UPDATE tickets SET invoice_id = ?, showtime_id = ?, seat_number = ?, " +
                     "ticket_price = ?, seat_type = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getInvoiceId());
            stmt.setInt(2, ticket.getShowtimeId());
            stmt.setString(3, ticket.getSeatNumber());
            stmt.setDouble(4, ticket.getTicketPrice());
            stmt.setString(5, ticket.getSeatType());
            stmt.setString(6, ticket.getStatus());
            stmt.setInt(7, ticket.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateTicket: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update ticket status
     */
    public boolean updateTicketStatus(int ticketId, String status) {
        String sql = "UPDATE tickets SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, ticketId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateTicketStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cancel tickets by invoice ID
     */
    public boolean cancelTicketsByInvoiceId(int invoiceId) {
        String sql = "UPDATE tickets SET status = 'CANCELLED' WHERE invoice_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in cancelTicketsByInvoiceId: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete ticket
     */
    public boolean deleteTicket(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteTicket: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get ticket count by showtime
     */
    public int getTicketCountByShowtime(int showtimeId) {
        String sql = "SELECT COUNT(*) FROM tickets WHERE showtime_id = ? AND status = 'SOLD'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error in getTicketCountByShowtime: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Extract Ticket object from ResultSet
     */
    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setInvoiceId(rs.getInt("invoice_id"));
        ticket.setShowtimeId(rs.getInt("showtime_id"));
        ticket.setSeatNumber(rs.getString("seat_number"));
        ticket.setTicketPrice(rs.getDouble("ticket_price"));
        ticket.setSeatType(rs.getString("seat_type"));
        ticket.setStatus(rs.getString("status"));
        ticket.setCreatedAt(rs.getTimestamp("created_at"));
        return ticket;
    }
}
