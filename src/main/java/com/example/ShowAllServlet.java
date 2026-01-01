package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/show_all")
public class ShowAllServlet extends HttpServlet {

    private final String jdbcURL = "jdbc:postgresql://localhost:5432/studentdb";
    private final String dbUser = "postgres";
    private final String dbPassword = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String[]> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, email, year FROM students");
            while (rs.next()) {
                students.add(new String[]{
                    rs.getString("name"),
                    rs.getString("email"),
                    String.valueOf(rs.getInt("year"))
                });
            }
        } catch (SQLException e) {
            resp.getWriter().println("Database error: " + e.getMessage());
            return;
        }

        req.setAttribute("students", students);
        try {
            req.getRequestDispatcher("show_all.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().println("Error rendering JSP: " + e.getMessage());
        }
    }
}
