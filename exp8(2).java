import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h1>Employee Details</h1>");
                out.println("<p>ID: " + rs.getInt("id") + "</p>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Department: " + rs.getString("department") + "</p>");
            } else {
                out.println("<h1>No Employee Found</h1>");
            }
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
// Database Setup in MySQL 
//CREATE TABLE employees (
    //id INT PRIMARY KEY,
    //name VARCHAR(100),
    //department VARCHAR(50)
//);

//INSERT INTO employees VALUES (1, 'John Doe', 'IT');
//Html search form in .html
//<form action="EmployeeServlet" method="get">
    //Enter Employee ID: <input type="text" name="id">
    //<input type="submit" value="Search">
//</form>
