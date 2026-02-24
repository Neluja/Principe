<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }

    String bookingId = request.getParameter("bookingId");
    String amount = request.getParameter("amount");

    if (bookingId == null || amount == null) {
        response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-5">

<div class="card shadow p-4">

    <h3 class="mb-4">Card Payment</h3>

    <!-- Payment Summary -->
    <div class="alert alert-info">
        <strong>Booking ID:</strong> <%= bookingId %><br>
        <strong>Total Amount:</strong> $<%= amount %>
    </div>

    <form action="<%= request.getContextPath() %>/TransactionServlet" method="post">

        <!-- Hidden Data -->
        <input type="hidden" name="bookingId" value="<%= bookingId %>">
        <input type="hidden" name="amount" value="<%= amount %>">

        <!-- Card Number -->
        <div class="mb-3">
            <label class="form-label">Card Number</label>
            <input type="text" name="cardNumber" class="form-control"
                   placeholder="1234 5678 9012 3456"
                   required maxlength="16">
        </div>

        <!-- Card Holder -->
        <div class="mb-3">
            <label class="form-label">Card Holder Name</label>
            <input type="text" name="cardName" class="form-control" required>
        </div>

        <!-- Expiry -->
        <div class="mb-3">
            <label class="form-label">Expiry Date</label>
            <input type="month" name="expiry" class="form-control" required>
        </div>

        <!-- CVV -->
        <div class="mb-3">
            <label class="form-label">CVV</label>
            <input type="password" name="cvv" class="form-control"
                   required maxlength="4">
        </div>

        <!-- Buttons -->
        <div class="d-flex justify-content-between">
            <a href="<%= request.getContextPath() %>/RoomServlet?action=list"
               class="btn btn-secondary">
                Cancel
            </a>

            <button type="submit" class="btn btn-success">
                Confirm Payment
            </button>
        </div>

    </form>

</div>

</body>
</html>