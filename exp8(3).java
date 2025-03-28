//Database Setup in MySQl
//CREATE TABLE attendance (
    //student_id INT PRIMARY KEY,
    //name VARCHAR(100),
    //status VARCHAR(10)
//);

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO attendance VALUES (?, ?, ?)");
            ps.setInt(1, studentId);
            ps.setString(2, name);
            ps.setString(3, status);
            ps.executeUpdate();

            response.getWriter().println("<h1>Attendance Recorded</h1>");
        } catch (Exception e) {
            response.getWriter().println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
