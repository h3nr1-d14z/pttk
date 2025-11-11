package com.cineman.dao;

import com.cineman.model.Movie;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Movie operations
 */
public class MovieDAO {

    /**
     * Get movie by ID
     */
    public Movie getMovieById(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractMovieFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getMovieById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all movies
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY release_date DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                movies.add(extractMovieFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllMovies: " + e.getMessage());
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Get movies by status
     */
    public List<Movie> getMoviesByStatus(String status) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE status = ? ORDER BY release_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(extractMovieFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getMoviesByStatus: " + e.getMessage());
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Get now showing movies
     */
    public List<Movie> getNowShowingMovies() {
        return getMoviesByStatus("NOW_SHOWING");
    }

    /**
     * Get coming soon movies
     */
    public List<Movie> getComingSoonMovies() {
        return getMoviesByStatus("COMING_SOON");
    }

    /**
     * Search movies by title
     */
    public List<Movie> searchMoviesByTitle(String keyword) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE title LIKE ? OR english_title LIKE ? ORDER BY release_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(extractMovieFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in searchMoviesByTitle: " + e.getMessage());
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Search movies by genre
     */
    public List<Movie> searchMoviesByGenre(String genre) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE genre LIKE ? ORDER BY release_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + genre + "%";
            stmt.setString(1, searchPattern);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(extractMovieFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in searchMoviesByGenre: " + e.getMessage());
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Insert new movie
     */
    public boolean insertMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, english_title, director, actors, genre, country, duration, " +
                     "description, poster_url, trailer_url, release_date, end_date, rating, age_rating, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getEnglishTitle());
            stmt.setString(3, movie.getDirector());
            stmt.setString(4, movie.getActors());
            stmt.setString(5, movie.getGenre());
            stmt.setString(6, movie.getCountry());
            stmt.setInt(7, movie.getDuration());
            stmt.setString(8, movie.getDescription());
            stmt.setString(9, movie.getPosterUrl());
            stmt.setString(10, movie.getTrailerUrl());
            stmt.setDate(11, movie.getReleaseDate());
            stmt.setDate(12, movie.getEndDate());
            stmt.setDouble(13, movie.getRating());
            stmt.setString(14, movie.getAgeRating());
            stmt.setString(15, movie.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in insertMovie: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update movie
     */
    public boolean updateMovie(Movie movie) {
        String sql = "UPDATE movies SET title = ?, english_title = ?, director = ?, actors = ?, genre = ?, " +
                     "country = ?, duration = ?, description = ?, poster_url = ?, trailer_url = ?, " +
                     "release_date = ?, end_date = ?, rating = ?, age_rating = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getEnglishTitle());
            stmt.setString(3, movie.getDirector());
            stmt.setString(4, movie.getActors());
            stmt.setString(5, movie.getGenre());
            stmt.setString(6, movie.getCountry());
            stmt.setInt(7, movie.getDuration());
            stmt.setString(8, movie.getDescription());
            stmt.setString(9, movie.getPosterUrl());
            stmt.setString(10, movie.getTrailerUrl());
            stmt.setDate(11, movie.getReleaseDate());
            stmt.setDate(12, movie.getEndDate());
            stmt.setDouble(13, movie.getRating());
            stmt.setString(14, movie.getAgeRating());
            stmt.setString(15, movie.getStatus());
            stmt.setInt(16, movie.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateMovie: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update movie status
     */
    public boolean updateMovieStatus(int movieId, String status) {
        String sql = "UPDATE movies SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, movieId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateMovieStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete movie
     */
    public boolean deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteMovie: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extract Movie object from ResultSet
     */
    private Movie extractMovieFromResultSet(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setEnglishTitle(rs.getString("english_title"));
        movie.setDirector(rs.getString("director"));
        movie.setActors(rs.getString("actors"));
        movie.setGenre(rs.getString("genre"));
        movie.setCountry(rs.getString("country"));
        movie.setDuration(rs.getInt("duration"));
        movie.setDescription(rs.getString("description"));
        movie.setPosterUrl(rs.getString("poster_url"));
        movie.setTrailerUrl(rs.getString("trailer_url"));
        movie.setReleaseDate(rs.getDate("release_date"));
        movie.setEndDate(rs.getDate("end_date"));
        movie.setRating(rs.getDouble("rating"));
        movie.setAgeRating(rs.getString("age_rating"));
        movie.setStatus(rs.getString("status"));
        movie.setCreatedAt(rs.getTimestamp("created_at"));
        return movie;
    }
}
