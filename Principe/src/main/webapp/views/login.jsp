<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>

<h2>User Login</h2>

<form action="../UserLoginServlet" method="post">
    Username: <input type="text" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="Login">
</form>

<a href="register.jsp">Create Account</a>

<% if(request.getParameter("error") != null){ %>
    <p style="color:red;">Invalid Login!</p>
<% } %>

</body>
</html>