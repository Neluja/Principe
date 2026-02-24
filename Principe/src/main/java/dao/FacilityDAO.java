package dao;

import java.sql.*;
import java.util.*;
import model.Facility;
import util.DBConnection;

public class FacilityDAO {

    // INSERT
    public boolean insert(Facility f) {

        String sql = "INSERT INTO facility(facility_name, description) VALUES(?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getFacilityName());
            ps.setString(2, f.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE
    public boolean update(Facility f) {

        String sql = "UPDATE facility SET facility_name=?, description=? WHERE facility_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getFacilityName());
            ps.setString(2, f.getDescription());
            ps.setInt(3, f.getFacilityId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public boolean delete(int id) {

        String sql = "DELETE FROM facility WHERE facility_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET ALL
    public List<Facility> getAll() {

        List<Facility> list = new ArrayList<>();

        String sql = "SELECT * FROM facility";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Facility f = new Facility();

                f.setFacilityId(rs.getInt("facility_id"));
                f.setFacilityName(rs.getString("facility_name"));
                f.setDescription(rs.getString("description"));

                list.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}