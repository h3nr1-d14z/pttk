package com.cineman.model;

import java.sql.Timestamp;

/**
 * DTO for showtime revenue statistics
 */
public class ShowtimeStatistics {
    private int showtimeId;
    private int movieId;
    private String movieTitle;
    private int roomId;
    private String roomName;
    private Timestamp showtime;
    private int totalTickets;
    private double totalRevenue;

    public ShowtimeStatistics() {
    }

    public ShowtimeStatistics(int showtimeId, int movieId, String movieTitle, int roomId,
                            String roomName, Timestamp showtime, int totalTickets, double totalRevenue) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.roomId = roomId;
        this.roomName = roomName;
        this.showtime = showtime;
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
