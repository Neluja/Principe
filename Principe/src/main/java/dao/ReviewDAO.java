package dao;

import java.sql.*;
import java.util.*;
import model.Review;
import util.DBConnection;

public class ReviewDAO {

    public boolean insert(Review r) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO review(user_id, room_id, rating, comments) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, r.getUserId());
            ps.setString(2, r.getRoomId());
            ps.setInt(3, r.getRating());
            ps.setString(4, r.getComments());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Review> getByRoom(String roomId) {
        List<Review> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM review WHERE room_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setReviewId(rs.getInt("review_id"));
                r.setRating(rs.getInt("rating"));
                r.setComments(rs.getString("comments"));
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}