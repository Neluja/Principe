package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import dao.BookingDAO;
import dao.RoomDAO;
import model.BookingDetail;
import model.Room;
import model.User;

public class BookingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        // ✅ Check login
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        try {

            // ==========================
            // Get Parameters
            // ==========================
            String roomId = request.getParameter("roomId");
            String checkInStr = request.getParameter("checkIn");
            String checkOutStr = request.getParameter("checkOut");
            String guestsStr = request.getParameter("guests");

            // Validate null
            if (roomId == null || checkInStr == null ||
                checkOutStr == null || guestsStr == null) {

                response.sendRedirect(request.getContextPath() + "/views/error.jsp");
                return;
            }

            int guests = Integer.parseInt(guestsStr);

            // ==========================
            // Get Room
            // ==========================
            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomById(roomId);

            if (room == null) {
                response.sendRedirect(request.getContextPath() + "/views/error.jsp");
                return;
            }

            // ==========================
            // Date Calculation
            // ==========================
            LocalDate checkIn = LocalDate.parse(checkInStr);
            LocalDate checkOut = LocalDate.parse(checkOutStr);

            long days = ChronoUnit.DAYS.between(checkIn, checkOut);

            if (days <= 0) {
                response.sendRedirect(request.getContextPath()
                        + "/views/booking.jsp?roomId=" + roomId);
                return;
            }

            // ==========================
            // Calculate Total
            // ==========================
            BigDecimal totalAmount =
                    room.getPrice().multiply(BigDecimal.valueOf(days));

            // ==========================
            // Create Booking Object
            // ==========================
            BookingDetail booking = new BookingDetail();
            booking.setUserId(user.getUserId());
            booking.setRoomId(roomId);
            booking.setCheckinDate(Date.from(
                    checkIn.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            booking.setCheckoutDate(Date.from(
                    checkOut.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            booking.setNoOfGuests(guests);
            booking.setTotalAmount(totalAmount);
            booking.setStatus("PENDING");

            // ==========================
            // Insert Booking
            // ==========================
            BookingDAO bookingDAO = new BookingDAO();
            int bookingId = bookingDAO.insertBooking(booking);

            if (bookingId > 0) {

                // Redirect to payment page
                response.sendRedirect(request.getContextPath()
                        + "/views/transaction.jsp?bookingId="
                        + bookingId
                        + "&amount="
                        + totalAmount);

            } else {
                response.sendRedirect(request.getContextPath()
                        + "/views/error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()
                    + "/views/error.jsp");
        }
    }
}