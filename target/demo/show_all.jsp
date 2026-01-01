<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
    List<String[]> students = (List<String[]>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Students</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">All Registered Students</h2>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Year</th>
            </tr>
        </thead>
        <tbody>
        <% if (students != null) {
               for (String[] s : students) { %>
            <tr>
                <td><%= s[0] %></td>
                <td><%= s[1] %></td>
                <td><%= s[2] %></td>
            </tr>
        <%   }
           } %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-primary">Register Another Student</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
