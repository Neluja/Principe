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

<h2>Admin Details</h2>

<p>ID: <%=admin.getAdminId()%></p>
<p>Username: <%=admin.getUsername()%></p>
<p>Password: <%=admin.getPassword()%></p>
<p>Name: <%=admin.getName()%></p>
<p>Role: <%=admin.getRole()%></p>

<img src="../../AdminImageServlet?id=<%=admin.getAdminId()%>" width="120">