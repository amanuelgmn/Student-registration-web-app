<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <h2>Student Registration WebApp</h2>
    <div class="container mt-5">
    <h2 class="mb-4">Register Student</h2>

    <% String error = request.getParameter("error");
       if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="register" method="post">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" class="form-control" name="name" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Year</label>
            <input type="number" class="form-control" name="year" required min="1" max="10">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
        <a href="show_all" class="btn btn-secondary">View All Students</a>
    </form>
</div>


    <!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
document.querySelector('form').addEventListener('submit', function(e) {
    const email = document.querySelector('input[name="email"]').value;
    const year = document.querySelector('input[name="year"]').value;
    if (!email.includes('@')) {
        alert('Please enter a valid email.');
        e.preventDefault();
    }
    if (year < 1 || year > 10) {
        alert('Year must be between 1 and 10.');
        e.preventDefault();
    }
});
</script>

</body>
</html>
