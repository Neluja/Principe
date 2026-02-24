package dao;

import java.sql.*;
import java.util.*;
import model.Offer;
import util.DBConnection;

public class OfferDAO {

    public List<Offer> getAll() {
        List<Offer> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM offer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Offer o = new Offer();
                o.setOfferId(rs.getString("offer_id"));
                o.setOfferName(rs.getString("offer_name"));
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}