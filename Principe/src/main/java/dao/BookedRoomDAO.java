package dao;

import java.sql.*;
import model.BookedRooms;
import util.DBConnection;

public class BookedRoomDAO {

    public boolean insert(BookedRooms b) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO bookedRooms(booking_id, room_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, b.getBookingId());
            ps.setString(2, b.getRoomId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}