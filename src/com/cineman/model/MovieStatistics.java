package com.cineman.model;

/**
 * DTO for movie revenue statistics
 */
public class MovieStatistics {
    private int movieId;
    private String title;
    private String genre;
    private int totalTickets;
    private double totalRevenue;

    public MovieStatistics() {
    }

    public MovieStatistics(int movieId, String title, String genre, int totalTickets, double totalRevenue) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
