package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final String jdbcURL = "jdbc:postgresql://localhost:5432/studentdb";
    private final String dbUser = "postgres";
    private final String dbPassword = "1234";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String yearStr = req.getParameter("year");

        resp.setContentType("text/html");
        var out = resp.getWriter();

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || yearStr.isEmpty()) {
            out.println("All fields are required.");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            out.println("Year must be a number.");
            return;
        }

        // Insert into DB
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, year);
            stmt.executeUpdate();

            // Redirect to view all students
            resp.sendRedirect("show_all");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                out.println("Email already exists. <a href='index.jsp'>Go back</a>");
            } else {
                out.println("Database error: " + e.getMessage());
            }
        }
    }
}
