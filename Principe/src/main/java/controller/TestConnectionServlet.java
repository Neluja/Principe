package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import util.DBConnection;

public class TestConnectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = DBConnection.getConnection();

        if (con != null) {
            out.println("<h2>Database Connected Successfully!</h2>");
        } else {
            out.println("<h2>Database Connection Failed!</h2>");
        }
    }
}