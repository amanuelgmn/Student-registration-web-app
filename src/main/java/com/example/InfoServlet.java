package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");

        var out = resp.getWriter();
        out.println("<h2>Request Information</h2>");

        out.println("<p><b>Method:</b> " + req.getMethod() + "</p>");
        out.println("<p><b>URI:</b> " + req.getRequestURI() + "</p>");
        out.println("<p><b>Client IP:</b> " + req.getRemoteAddr() + "</p>");

        out.println("<h3>Headers</h3>");
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String h = headers.nextElement();
            out.println(h + " : " + req.getHeader(h) + "<br>");
        }
    }
}
