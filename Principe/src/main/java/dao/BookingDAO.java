package dao;

import java.sql.*;
import model.BookingDetail;
import util.DBConnection;

public class BookingDAO {

    // INSERT BOOKING
    public int insertBooking(BookingDetail booking) {

        int bookingId = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO booking_detail " +
                    "(user_id, room_id, booking_date, checkin_date, checkout_date, no_of_guests, total_amount, status) " +
                    "VALUES (?, ?, NOW(), ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, booking.getUserId());
            ps.setString(2, booking.getRoomId());
            ps.setDate(3, new java.sql.Date(booking.getCheckinDate().getTime()));
            ps.setDate(4, new java.sql.Date(booking.getCheckoutDate().getTime()));
            ps.setInt(5, booking.getNoOfGuests());
            ps.setBigDecimal(6, booking.getTotalAmount());
            ps.setString(7, booking.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookingId;
    }
 // UPDATE BOOKING STATUS
    public boolean updateStatus(int bookingId, String status) {

        boolean result = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE booking_detail SET status=? WHERE booking_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, bookingId);

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public BookingDetail getBookingById(int bookingId) {

        BookingDetail booking = null;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM booking_detail WHERE booking_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new BookingDetail();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setRoomId(rs.getString("room_id"));
                booking.setUserId(rs.getInt("user_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return booking;
    }
}