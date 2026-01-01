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

        if (name.isEmpty() || email.isEmpty() || yearStr.isEmpty()) {
            resp.sendRedirect("index.jsp?error=All+fields+required");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("index.jsp?error=Year+must+be+a+number");
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                String sql = "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setInt(3, year);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key")) {
                resp.sendRedirect("index.jsp?error=Email+already+exists");
            } else {
                resp.sendRedirect("index.jsp?error=Database+error");
            }
            return;
        } catch (ClassNotFoundException e) {
            resp.sendRedirect("index.jsp?error=Driver+not+found");
            return;
        }

        // Redirect to show all students
        resp.sendRedirect("show_all");
    }
}
