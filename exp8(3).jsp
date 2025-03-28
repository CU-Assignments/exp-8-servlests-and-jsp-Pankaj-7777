//JSP form
<form action="AttendanceServlet" method="post">
    Student ID: <input type="text" name="student_id"><br>
    Name: <input type="text" name="name"><br>
    Status: 
    <select name="status">
        <option value="Present">Present</option>
        <option value="Absent">Absent</option>
    </select><br>
    <input type="submit" value="Submit">
</form>
