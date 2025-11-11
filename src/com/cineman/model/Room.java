package com.cineman.model;

import java.sql.Timestamp;

/**
 * Model class for Cinema Room
 */
public class Room {
    private int id;
    private String roomName;
    private int totalSeats;
    private int totalRows;
    private int seatsPerRow;
    private String roomType; // 2D, 3D, IMAX, VIP
    private String status; // ACTIVE, MAINTENANCE, INACTIVE
    private Timestamp createdAt;

    // Empty constructor
    public Room() {
    }

    // Full constructor
    public Room(int id, String roomName, int totalSeats, int totalRows,
                int seatsPerRow, String roomType, String status, Timestamp createdAt) {
        this.id = id;
        this.roomName = roomName;
        this.totalSeats = totalSeats;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.roomType = roomType;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
