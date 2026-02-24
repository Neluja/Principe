package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import model.RoomImage;

public class RoomImageDAO {

    // INSERT IMAGE
    public void addImage(String roomId, byte[] imageData) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO room_image (room_id, room_image) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);
            ps.setBytes(2, imageData);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE ALL IMAGES OF ROOM (for update)
    public void deleteByRoomId(String roomId) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM room_image WHERE room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET IMAGES BY ROOM
    public List<RoomImage> getImagesByRoom(String roomId) {

        List<RoomImage> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM room_image WHERE room_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RoomImage img = new RoomImage();
                img.setRoomImageId(rs.getInt("roomimage_id"));
                img.setRoomId(rs.getString("room_id"));
                img.setRoomImage(rs.getBytes("room_image"));

                list.add(img);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void deleteByImageId(int imageId) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM room_image WHERE roomimage_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, imageId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}