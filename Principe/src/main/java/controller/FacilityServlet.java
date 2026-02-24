package controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import dao.FacilityDAO;
import model.Facility;

public class FacilityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        FacilityDAO dao = new FacilityDAO();

        if ("add".equals(action)) {

            Facility f = new Facility();

            f.setFacilityName(request.getParameter("facilityName"));
            f.setDescription(request.getParameter("description"));

            dao.insert(f);

            response.sendRedirect("views/facility/listFacility.jsp");

        } else if ("update".equals(action)) {

            Facility f = new Facility();

            f.setFacilityId(Integer.parseInt(request.getParameter("facilityId")));
            f.setFacilityName(request.getParameter("facilityName"));
            f.setDescription(request.getParameter("description"));

            dao.update(f);

            response.sendRedirect("views/facility/listFacility.jsp");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        FacilityDAO dao = new FacilityDAO();

        if ("delete".equals(action)) {

            int id = Integer.parseInt(request.getParameter("facilityId"));
            dao.delete(id);

            response.sendRedirect("views/facility/listFacility.jsp");
        }
    }
}