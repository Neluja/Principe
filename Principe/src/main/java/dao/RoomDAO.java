package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Room;
import util.DBConnection;

public class RoomDAO {

    // INSERT
    public boolean insert(Room room) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO room (room_id, type, price, description, size, availability) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, room.getRoomId());
            ps.setString(2, room.getType());
            ps.setBigDecimal(3, room.getPrice());
            ps.setString(4, room.getDescription());
            ps.setString(5, room.getSize());
            ps.setString(6, room.getAvailability());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // GET ALL
    public List<Room> getAllRooms() {

        List<Room> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM room";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Room room = new Room();
                room.setRoomId(rs.getString("room_id"));
                room.setType(rs.getString("type"));
                room.setPrice(rs.getBigDecimal("price"));
                room.setDescription(rs.getString("description"));
                room.setSize(rs.getString("size"));
                room.setAvailability(rs.getString("availability"));

                list.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET BY ID
    public Room getRoomById(String id) {

        Room room = null;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM room WHERE room_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                room = new Room();
                room.setRoomId(rs.getString("room_id"));
                room.setType(rs.getString("type"));
                room.setPrice(rs.getBigDecimal("price"));
                room.setDescription(rs.getString("description"));
                room.setSize(rs.getString("size"));
                room.setAvailability(rs.getString("availability"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return room;
    }

    // UPDATE
    public boolean update(Room room) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE room SET type=?, price=?, description=?, size=?, availability=? WHERE room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, room.getType());
            ps.setBigDecimal(2, room.getPrice());
            ps.setString(3, room.getDescription());
            ps.setString(4, room.getSize());
            ps.setString(5, room.getAvailability());
            ps.setString(6, room.getRoomId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // DELETE
    public boolean delete(String id) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM room WHERE room_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public List<String> getFacilitiesByRoom(String roomId) {

        List<String> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT f.facility_name " +
            "FROM facility f " +
            "JOIN room_facility rf ON f.facility_id = rf.facility_id " +
            "WHERE rf.room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("facility_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean updateAvailability(String roomId, String status) {

        boolean result = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE room SET availability=? WHERE room_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setString(2, roomId);

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}