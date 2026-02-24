<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Room Details</title>
</head>
<body>

<h2>Room Details</h2>

<form action="../ReviewServlet" method="post">
    User ID: <input type="text" name="userId"><br>
    Room ID: <input type="text" name="roomId"><br>
    Rating (1-5): <input type="number" name="rating"><br>
    Comment: <textarea name="comments"></textarea><br>
    <input type="submit" value="Submit Review">
</form>

</body>
</html>