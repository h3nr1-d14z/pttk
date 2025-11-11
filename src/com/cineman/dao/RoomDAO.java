package com.cineman.dao;

import com.cineman.model.Room;
import com.cineman.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Room operations
 */
public class RoomDAO {

    /**
     * Get room by ID
     */
    public Room getRoomById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractRoomFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error in getRoomById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all rooms
     */
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(extractRoomFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllRooms: " + e.getMessage());
            e.printStackTrace();
        }

        return rooms;
    }

    /**
     * Get active rooms
     */
    public List<Room> getActiveRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE status = 'ACTIVE' ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(extractRoomFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getActiveRooms: " + e.getMessage());
            e.printStackTrace();
        }

        return rooms;
    }

    /**
     * Get rooms by type
     */
    public List<Room> getRoomsByType(String roomType) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE room_type = ? ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(extractRoomFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error in getRoomsByType: " + e.getMessage());
            e.printStackTrace();
        }

        return rooms;
    }

    /**
     * Insert new room
     */
    public boolean insertRoom(Room room) {
        String sql = "INSERT INTO rooms (room_name, total_seats, total_rows, seats_per_row, room_type, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getTotalSeats());
            stmt.setInt(3, room.getTotalRows());
            stmt.setInt(4, room.getSeatsPerRow());
            stmt.setString(5, room.getRoomType());
            stmt.setString(6, room.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in insertRoom: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update room
     */
    public boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_name = ?, total_seats = ?, total_rows = ?, seats_per_row = ?, " +
                     "room_type = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getTotalSeats());
            stmt.setInt(3, room.getTotalRows());
            stmt.setInt(4, room.getSeatsPerRow());
            stmt.setString(5, room.getRoomType());
            stmt.setString(6, room.getStatus());
            stmt.setInt(7, room.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateRoom: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update room status
     */
    public boolean updateRoomStatus(int roomId, String status) {
        String sql = "UPDATE rooms SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, roomId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in updateRoomStatus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete room
     */
    public boolean deleteRoom(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error in deleteRoom: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extract Room object from ResultSet
     */
    private Room extractRoomFromResultSet(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setRoomName(rs.getString("room_name"));
        room.setTotalSeats(rs.getInt("total_seats"));
        room.setTotalRows(rs.getInt("total_rows"));
        room.setSeatsPerRow(rs.getInt("seats_per_row"));
        room.setRoomType(rs.getString("room_type"));
        room.setStatus(rs.getString("status"));
        room.setCreatedAt(rs.getTimestamp("created_at"));
        return room;
    }
}
