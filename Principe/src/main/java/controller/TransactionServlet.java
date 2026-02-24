package controller;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import dao.TransactionDAO;
import dao.BookingDAO;
import dao.RoomDAO;
import model.User;
import model.BookingDetail;
import util.EmailUtil;

public class TransactionServlet extends HttpServlet {

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
            String bookingIdStr = request.getParameter("bookingId");
            String amountStr = request.getParameter("amount");

            if (bookingIdStr == null || amountStr == null) {
                response.sendRedirect(request.getContextPath() + "/views/error.jsp");
                return;
            }

            int bookingId = Integer.parseInt(bookingIdStr);
            BigDecimal amount = new BigDecimal(amountStr);

            // ==========================
            // Insert Transaction
            // ==========================
            TransactionDAO transactionDAO = new TransactionDAO();

            // Short transaction id (safe for DB)
            String transactionId = "TXN" + System.currentTimeMillis();

            boolean transactionSuccess = transactionDAO.insertTransaction(
                    transactionId,
                    bookingId,
                    user.getUserId(),
                    amount
            );

            if (transactionSuccess) {

                // ==========================
                // Update Booking Status
                // ==========================
                BookingDAO bookingDAO = new BookingDAO();
                bookingDAO.updateStatus(bookingId, "CONFIRMED");

                // ==========================
                // Get Booking to Find Room
                // ==========================
                BookingDetail booking = bookingDAO.getBookingById(bookingId);

                if (booking != null) {

                    // ==========================
                    // Update Room Availability
                    // ==========================
                    RoomDAO roomDAO = new RoomDAO();
                    roomDAO.updateAvailability(
                            booking.getRoomId(),
                            "NOT_AVAILABLE"
                    );
                }

                // ==========================
                // Send Email
                // ==========================
                String message =
                        "Payment Successful!\n\n" +
                        "Transaction ID: " + transactionId + "\n" +
                        "Booking ID: " + bookingId + "\n" +
                        "Amount: " + amount + "\n\n" +
                        "Your booking is CONFIRMED.";

                EmailUtil.sendBill(
                        user.getEmail(),
                        "Booking Confirmation",
                        message
                );

                // Redirect to success page
                response.sendRedirect(request.getContextPath() + "/views/success.jsp");

            } else {
                response.sendRedirect(request.getContextPath() + "/views/error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        }
    }
}