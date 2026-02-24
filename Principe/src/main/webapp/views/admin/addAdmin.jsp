<form action="<%=request.getContextPath()%>/AdminServlet"
      method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="action" value="add">

    Admin ID:
    <input type="text" name="adminId"><br><br>

    Username:
    <input type="text" name="username"><br><br>

    Password:
    <input type="password" name="password"><br><br>

    Name:
    <input type="text" name="name"><br><br>

    Role:
    <input type="text" name="role"><br><br>

    <!-- Image Upload -->
    Image:
    <input type="file" name="image" accept="image/*"><br><br>

    <button type="submit">Add Admin</button>

</form>