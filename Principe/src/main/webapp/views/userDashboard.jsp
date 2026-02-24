<%@ page language="java" %>
<%
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>

<h2>User Dashboard</h2>

<!-- Access through Servlet -->
<a href="<%= request.getContextPath() %>/RoomServlet?action=userList">
    View Available Rooms
</a>
<br><br>

<a href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>

</body>
</html>