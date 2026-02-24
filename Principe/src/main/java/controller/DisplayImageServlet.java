package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import util.DBConnection;

public class DisplayImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT room_image FROM room_image WHERE roomimage_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                byte[] imageData = rs.getBytes("room_image");

                if (imageData != null) {

                    // IMPORTANT: Set content type properly
                    response.setContentType("image/jpeg");

                    response.setContentLength(imageData.length);

                    OutputStream out = response.getOutputStream();
                    out.write(imageData);
                    out.flush();
                    out.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}