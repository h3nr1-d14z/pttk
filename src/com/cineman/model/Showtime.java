package com.cineman.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Model class for Showtime
 */
public class Showtime {
    private int id;
    private int movieId;
    private int roomId;
    private Date showDate;
    private Time startTime;
    private Time endTime;
    private double ticketPrice;
    private int availableSeats;
    private String status; // SCHEDULED, ONGOING, FINISHED, CANCELLED
    private Timestamp createdAt;

    // Empty constructor
    public Showtime() {
    }

    // Full constructor
    public Showtime(int id, int movieId, int roomId, Date showDate, Time startTime,
                    Time endTime, double ticketPrice, int availableSeats, String status, Timestamp createdAt) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
