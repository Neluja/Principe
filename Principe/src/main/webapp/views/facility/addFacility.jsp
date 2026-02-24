<form action="<%=request.getContextPath()%>/FacilityServlet"
      method="post">

    <input type="hidden" name="action" value="add">

    Facility Name:
    <input type="text" name="facilityName"><br>

    Description:
    <textarea name="description"></textarea><br>

    <button type="submit">Add</button>
</form>