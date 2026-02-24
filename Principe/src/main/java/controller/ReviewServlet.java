package controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import dao.ReviewDAO;
import model.Review;

public class ReviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Review r = new Review();
        r.setUserId(Integer.parseInt(request.getParameter("userId")));
        r.setRoomId(request.getParameter("roomId"));
        r.setRating(Integer.parseInt(request.getParameter("rating")));
        r.setComments(request.getParameter("comments"));

        ReviewDAO dao = new ReviewDAO();
        dao.insert(r);

        response.sendRedirect("views/roomDetails.jsp");
    }
}