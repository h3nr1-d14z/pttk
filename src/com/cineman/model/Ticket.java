package com.cineman.model;

import java.sql.Timestamp;

/**
 * Model class for Ticket
 */
public class Ticket {
    private int id;
    private int invoiceId;
    private int showtimeId;
    private String seatNumber; // A1, B5, C10...
    private double ticketPrice;
    private String seatType; // NORMAL, VIP, COUPLE
    private String status; // AVAILABLE, BOOKED, SOLD, CANCELLED
    private Timestamp createdAt;

    // Empty constructor
    public Ticket() {
    }

    // Full constructor
    public Ticket(int id, int invoiceId, int showtimeId, String seatNumber, double ticketPrice,
                  String seatType, String status, Timestamp createdAt) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.ticketPrice = ticketPrice;
        this.seatType = seatType;
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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
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
