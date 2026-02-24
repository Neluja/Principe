<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>User Registration</h2>

<form action="../UserRegisterServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    Name: <input type="text" name="name"><br>
    Email: <input type="email" name="email"><br>
    Telephone: <input type="text" name="tel"><br>
    Address: <input type="text" name="address"><br><br>
    <input type="submit" value="Register">
</form>

</body>
</html>