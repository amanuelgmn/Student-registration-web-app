<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>

<%
    List<String[]> students = (List<String[]>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Students</title>
</head>
<body>
    <h2>All Registered Students</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Year</th>
        </tr>
        <%
            if (students != null) {
                for (String[] s : students) {
        %>
        <tr>
            <td><%= s[0] %></td>
            <td><%= s[1] %></td>
            <td><%= s[2] %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Register Student</a>
</body>
</html>
