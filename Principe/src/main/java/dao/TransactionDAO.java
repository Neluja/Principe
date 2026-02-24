package dao;

import java.sql.*;
import util.DBConnection;

public class TransactionDAO {

    public boolean insertTransaction(String transactionId,
                                     int bookingId,
                                     int userId,
                                     java.math.BigDecimal amount) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO transaction_table " +
                    "(transaction_id, booking_id, user_id, transaction_date, amount, payment_method, status) " +
                    "VALUES (?, ?, ?, NOW(), ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, transactionId);
            ps.setInt(2, bookingId);
            ps.setInt(3, userId);
            ps.setBigDecimal(4, amount);
            ps.setString(5, "CARD");
            ps.setString(6, "SUCCESS");

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}