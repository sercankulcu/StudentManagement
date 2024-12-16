<!-- dashboard.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Dashboard</h1>
    
    
    <form action="StudentManagementServlet" method="post">
    <a href="StudentManagementServlet?action=listStudents" class="button">List All Students</a>
    <a href="StudentManagementServlet?action=listTop10Students" class="button">Top 10 Students</a>
    <a href="addStudent.jsp" class="button">Add New Student</a>
        <input type="hidden" name="action" value="logout">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
