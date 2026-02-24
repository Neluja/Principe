package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;

public class RoomFacilityDAO {

    // Add facility to room
    public void addRoomFacility(String roomId, int facilityId) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO room_facility (facility_id, room_id) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, facilityId);
            ps.setString(2, roomId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete all facilities of room (for update)
    public void deleteByRoomId(String roomId) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM room_facility WHERE room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get facility IDs of a room
    public List<Integer> getFacilityIdsByRoom(String roomId) {

        List<Integer> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT facility_id FROM room_facility WHERE room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("facility_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}