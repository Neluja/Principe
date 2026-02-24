<%@ page import="dao.FacilityDAO,model.Facility,java.util.*" %>

<%
    int id = Integer.parseInt(request.getParameter("facilityId"));
    FacilityDAO dao = new FacilityDAO();
    Facility facility = null;

    for(Facility f : dao.getAll()){
        if(f.getFacilityId() == id){
            facility = f;
            break;
        }
    }
%>

<h2>Edit Facility</h2>

<form action="<%=request.getContextPath()%>/FacilityServlet"
      method="post">

    <input type="hidden" name="action" value="update">
    <input type="hidden" name="facilityId"
           value="<%=facility.getFacilityId()%>">

    Facility Name:
    <input type="text" name="facilityName"
           value="<%=facility.getFacilityName()%>"><br><br>

    Description:
    <textarea name="description"><%=facility.getDescription()%></textarea><br><br>

    <button type="submit">Update</button>

</form>