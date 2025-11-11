package com.cineman.dao;

import com.cineman.model.Showtime;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Showtime operations
 */
public class ShowtimeDAO {

    /**
     * Get showtime by ID
     */
    public Showtime getShowtimeById(int id) {
        String sql = "SELECT * FROM showtimes WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractShowtimeFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimeById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all showtimes
     */
    public List<Showtime> getAllShowtimes() {
        List<Showtime> showtimes = new ArrayList<>();
        String sql = "SELECT * FROM showtimes ORDER BY show_date DESC, start_time DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                showtimes.add(extractShowtimeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllShowtimes: " + e.getMessage());
            e.printStackTrace();
        }

        return showtimes;
    }

    /**
     * Get showtimes by movie ID
     */
    public List<Showtime> getShowtimesByMovieId(int movieId) {
        List<Showtime> showtimes = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE movie_id = ? ORDER BY show_date, start_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                showtimes.add(extractShowtimeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimesByMovieId: " + e.getMessage());
            e.printStackTrace();
        }

        return showtimes;
    }

    /**
     * Get showtimes by date
     */
    public List<Showtime> getShowtimesByDate(Date showDate) {
        List<Showtime> showtimes = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE show_date = ? ORDER BY start_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, showDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                showtimes.add(extractShowtimeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimesByDate: " + e.getMessage());
            e.printStackTrace();
        }

        return showtimes;
    }

    /**
     * Get showtimes by movie and date
     */
    public List<Showtime> getShowtimesByMovieAndDate(int movieId, Date showDate) {
        List<Showtime> showtimes = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE movie_id = ? AND show_date = ? ORDER BY start_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movieId);
            stmt.setDate(2, showDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                showtimes.add(extractShowtimeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimesByMovieAndDate: " + e.getMessage());
            e.printStackTrace();
        }

        return showtimes;
    }

    /**
     * Get showtimes by room ID
     */
    public List<Showtime> getShowtimesByRoomId(int roomId) {
        List<Showtime> showtimes = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE room_id = ? ORDER BY show_date DESC, start_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                showtimes.add(extractShowtimeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimesByRoomId: " + e.getMessage());
            e.printStackTrace();
        }

        return showtimes;
    }

    /**
     * Insert new showtime
     */
    public boolean insertShowtime(Showtime showtime) {
        String sql = "INSERT INTO showtimes (movie_id, room_id, show_date, start_time, end_time, " +
                     "ticket_price, available_seats, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtime.getMovieId());
            stmt.setInt(2, showtime.getRoomId());
            stmt.setDate(3, showtime.getShowDate());
            stmt.setTime(4, showtime.getStartTime());
            stmt.setTime(5, showtime.getEndTime());
            stmt.setDouble(6, showtime.getTicketPrice());
            stmt.setInt(7, showtime.getAvailableSeats());
            stmt.setString(8, showtime.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in insertShowtime: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update showtime
     */
    public boolean updateShowtime(Showtime showtime) {
        String sql = "UPDATE showtimes SET movie_id = ?, room_id = ?, show_date = ?, start_time = ?, " +
                     "end_time = ?, ticket_price = ?, available_seats = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtime.getMovieId());
            stmt.setInt(2, showtime.getRoomId());
            stmt.setDate(3, showtime.getShowDate());
            stmt.setTime(4, showtime.getStartTime());
            stmt.setTime(5, showtime.getEndTime());
            stmt.setDouble(6, showtime.getTicketPrice());
            stmt.setInt(7, showtime.getAvailableSeats());
            stmt.setString(8, showtime.getStatus());
            stmt.setInt(9, showtime.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateShowtime: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update available seats
     */
    public boolean updateAvailableSeats(int showtimeId, int availableSeats) {
        String sql = "UPDATE showtimes SET available_seats = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, availableSeats);
            stmt.setInt(2, showtimeId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateAvailableSeats: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Decrease available seats (when tickets are sold)
     */
    public boolean decreaseAvailableSeats(int showtimeId, int count) {
        String sql = "UPDATE showtimes SET available_seats = available_seats - ? WHERE id = ? AND available_seats >= ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, count);
            stmt.setInt(2, showtimeId);
            stmt.setInt(3, count);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in decreaseAvailableSeats: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update showtime status
     */
    public boolean updateShowtimeStatus(int showtimeId, String status) {
        String sql = "UPDATE showtimes SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, showtimeId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateShowtimeStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete showtime
     */
    public boolean deleteShowtime(int id) {
        String sql = "DELETE FROM showtimes WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteShowtime: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if room is available at given date/time
     */
    public boolean isRoomAvailable(int roomId, Date showDate, Time startTime, Time endTime, Integer excludeShowtimeId) {
        String sql = "SELECT COUNT(*) FROM showtimes WHERE room_id = ? AND show_date = ? " +
                     "AND ((start_time < ? AND end_time > ?) OR (start_time < ? AND end_time > ?) " +
                     "OR (start_time >= ? AND end_time <= ?)) AND status != 'CANCELLED'";

        if (excludeShowtimeId != null) {
            sql += " AND id != ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);
            stmt.setDate(2, showDate);
            stmt.setTime(3, endTime);
            stmt.setTime(4, startTime);
            stmt.setTime(5, startTime);
            stmt.setTime(6, startTime);
            stmt.setTime(7, startTime);
            stmt.setTime(8, endTime);

            if (excludeShowtimeId != null) {
                stmt.setInt(9, excludeShowtimeId);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            System.err.println("Error in isRoomAvailable: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Extract Showtime object from ResultSet
     */
    private Showtime extractShowtimeFromResultSet(ResultSet rs) throws SQLException {
        Showtime showtime = new Showtime();
        showtime.setId(rs.getInt("id"));
        showtime.setMovieId(rs.getInt("movie_id"));
        showtime.setRoomId(rs.getInt("room_id"));
        showtime.setShowDate(rs.getDate("show_date"));
        showtime.setStartTime(rs.getTime("start_time"));
        showtime.setEndTime(rs.getTime("end_time"));
        showtime.setTicketPrice(rs.getDouble("ticket_price"));
        showtime.setAvailableSeats(rs.getInt("available_seats"));
        showtime.setStatus(rs.getString("status"));
        showtime.setCreatedAt(rs.getTimestamp("created_at"));
        return showtime;
    }
}
