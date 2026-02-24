<%@ page import="model.Admin,dao.AdminDAO" %>

<%
    String id = request.getParameter("adminId");
    AdminDAO dao = new AdminDAO();
    Admin admin = null;

    for(Admin a : dao.getAll()){
        if(a.getAdminId().equals(id)){
            admin = a;
            break;
        }
    }
%>

<h2>Edit Admin</h2>

<form action="<%=request.getContextPath()%>/AdminServlet"
      method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="action" value="update">

    <input type="hidden" name="adminId"
           value="<%=admin.getAdminId()%>">

    Username:
    <input type="text" name="username"
           value="<%=admin.getUsername()%>"><br><br>
           
            Password:
    <input type="text" name="password"
           value="<%=admin.getPassword()%>"><br><br>

    Name:
    <input type="text" name="name"
           value="<%=admin.getName()%>"><br><br>
           
   

    Role:
    <input type="text" name="role"
           value="<%=admin.getRole()%>"><br><br>

    <!-- Show Current Image -->
    <p>Current Image:</p>
    <img src="<%=request.getContextPath()%>/AdminImageServlet?id=<%=admin.getAdminId()%>"
         width="100" height="100"><br><br>

    <!-- New Image Upload -->
    Change Image:
    <input type="file" name="image" accept="image/*"><br><br>

    <button type="submit">Update</button>

</form>