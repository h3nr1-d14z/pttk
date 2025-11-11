package com.cineman.dao;

import com.cineman.model.MovieStatistics;
import com.cineman.model.ShowtimeStatistics;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for statistics queries
 */
public class StatisticsDAO {

    /**
     * Get movie revenue statistics by date range (Level 1)
     */
    public List<MovieStatistics> getMovieRevenueStatistics(Date startDate, Date endDate) {
        List<MovieStatistics> statistics = new ArrayList<>();
        String sql = "SELECT m.id as movie_id, m.title, m.genre, " +
                     "COUNT(t.id) as total_tickets, " +
                     "COALESCE(SUM(i.final_amount), 0) as total_revenue " +
                     "FROM movies m " +
                     "JOIN showtimes s ON m.id = s.movie_id " +
                     "LEFT JOIN invoices i ON s.id = i.showtime_id AND i.status = 'PAID' " +
                     "AND DATE(i.created_at) BETWEEN ? AND ? " +
                     "LEFT JOIN tickets t ON i.id = t.invoice_id " +
                     "GROUP BY m.id, m.title, m.genre " +
                     "HAVING total_revenue > 0 " +
                     "ORDER BY total_revenue DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MovieStatistics stat = new MovieStatistics();
                stat.setMovieId(rs.getInt("movie_id"));
                stat.setTitle(rs.getString("title"));
                stat.setGenre(rs.getString("genre"));
                stat.setTotalTickets(rs.getInt("total_tickets"));
                stat.setTotalRevenue(rs.getDouble("total_revenue"));
                statistics.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Error in getMovieRevenueStatistics: " + e.getMessage());
            e.printStackTrace();
        }

        return statistics;
    }

    /**
     * Get showtime revenue statistics for a specific movie (Level 2)
     */
    public List<ShowtimeStatistics> getShowtimeRevenueByMovie(int movieId, Date startDate, Date endDate) {
        List<ShowtimeStatistics> statistics = new ArrayList<>();
        String sql = "SELECT s.id as showtime_id, s.movie_id, m.title as movie_title, " +
                     "s.room_id, r.name as room_name, s.showtime, " +
                     "COUNT(t.id) as total_tickets, " +
                     "COALESCE(SUM(i.final_amount), 0) as total_revenue " +
                     "FROM showtimes s " +
                     "JOIN movies m ON s.movie_id = m.id " +
                     "JOIN rooms r ON s.room_id = r.id " +
                     "LEFT JOIN invoices i ON s.id = i.showtime_id AND i.status = 'PAID' " +
                     "AND DATE(i.created_at) BETWEEN ? AND ? " +
                     "LEFT JOIN tickets t ON i.id = t.invoice_id " +
                     "WHERE s.movie_id = ? " +
                     "GROUP BY s.id, s.movie_id, m.title, s.room_id, r.name, s.showtime " +
                     "HAVING total_revenue > 0 " +
                     "ORDER BY s.showtime DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            stmt.setInt(3, movieId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ShowtimeStatistics stat = new ShowtimeStatistics();
                stat.setShowtimeId(rs.getInt("showtime_id"));
                stat.setMovieId(rs.getInt("movie_id"));
                stat.setMovieTitle(rs.getString("movie_title"));
                stat.setRoomId(rs.getInt("room_id"));
                stat.setRoomName(rs.getString("room_name"));
                stat.setShowtime(rs.getTimestamp("showtime"));
                stat.setTotalTickets(rs.getInt("total_tickets"));
                stat.setTotalRevenue(rs.getDouble("total_revenue"));
                statistics.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Error in getShowtimeRevenueByMovie: " + e.getMessage());
            e.printStackTrace();
        }

        return statistics;
    }
}
