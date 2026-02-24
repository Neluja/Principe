package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import dao.RoomDAO;
import dao.RoomFacilityDAO;
import dao.RoomImageDAO;
import model.Room;

@MultipartConfig(maxFileSize = 16177215)
public class RoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        RoomDAO dao = new RoomDAO();
        RoomFacilityDAO facilityDAO = new RoomFacilityDAO();
        RoomImageDAO imageDAO = new RoomImageDAO();

        try {

            // ================= ADD ROOM =================
            if ("add".equals(action)) {

                Room room = new Room();
                room.setRoomId(request.getParameter("roomId"));
                room.setType(request.getParameter("type"));
                room.setPrice(new BigDecimal(request.getParameter("price")));
                room.setDescription(request.getParameter("description"));
                room.setSize(request.getParameter("size"));
                room.setAvailability(request.getParameter("availability"));

                dao.insert(room);

                String[] facilities = request.getParameterValues("facilityIds");

                if (facilities != null) {
                    for (String fid : facilities) {
                        facilityDAO.addRoomFacility(
                                room.getRoomId(),
                                Integer.parseInt(fid)
                        );
                    }
                }

                for (Part part : request.getParts()) {
                    if (part.getName().equals("roomImages") && part.getSize() > 0) {
                        byte[] imageData = part.getInputStream().readAllBytes();
                        imageDAO.addImage(room.getRoomId(), imageData);
                    }
                }

                response.sendRedirect("RoomServlet?action=list");
            }

            // ================= UPDATE ROOM =================
            else if ("update".equals(action)) {

                Room room = new Room();
                room.setRoomId(request.getParameter("roomId"));
                room.setType(request.getParameter("type"));
                room.setPrice(new BigDecimal(request.getParameter("price")));
                room.setDescription(request.getParameter("description"));
                room.setSize(request.getParameter("size"));
                room.setAvailability(request.getParameter("availability"));

                dao.update(room);

                facilityDAO.deleteByRoomId(room.getRoomId());

                String[] facilities = request.getParameterValues("facilityIds");

                if (facilities != null) {
                    for (String fid : facilities) {
                        facilityDAO.addRoomFacility(
                                room.getRoomId(),
                                Integer.parseInt(fid)
                        );
                    }
                }

                // Delete selected images only
                String[] deleteIds = request.getParameterValues("deleteImageIds");

                if (deleteIds != null) {
                    for (String id : deleteIds) {
                        imageDAO.deleteByImageId(Integer.parseInt(id));
                    }
                }

                // Add new images
                for (Part part : request.getParts()) {
                    if (part.getName().equals("roomImages") && part.getSize() > 0) {
                        byte[] imageData = part.getInputStream().readAllBytes();
                        imageDAO.addImage(room.getRoomId(), imageData);
                    }
                }

                response.sendRedirect("RoomServlet?action=list");
            }

            // ================= DELETE ROOM =================
            else if ("delete".equals(action)) {

                dao.delete(request.getParameter("roomId"));
                response.sendRedirect("RoomServlet?action=list");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET METHODS =================
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        RoomDAO dao = new RoomDAO();

        try {

            // ===== ADMIN LIST =====
            if ("list".equals(action)) {

                List<Room> list = dao.getAllRooms();
                request.setAttribute("roomList", list);

                RequestDispatcher rd =
                        request.getRequestDispatcher("views/room/manageRooms.jsp");

                rd.forward(request, response);
            }

            // ===== EDIT ROOM =====
            else if ("edit".equals(action)) {

                Room room = dao.getRoomById(request.getParameter("roomId"));

                request.setAttribute("room", room);

                RequestDispatcher rd =
                        request.getRequestDispatcher("views/room/editRoom.jsp");

                rd.forward(request, response);
            }

            // ===== USER VIEW ALL ROOMS =====
            else if ("userList".equals(action)) {

                List<Room> list = dao.getAllRooms();
                request.setAttribute("roomList", list);

                RequestDispatcher rd =
                        request.getRequestDispatcher("views/room/viewAllroomUser.jsp");

                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}