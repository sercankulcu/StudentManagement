<!-- updateStudent.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Update Student</h1>
    <form action="StudentManagementServlet" method="post">
        <input type="hidden" name="action" value="updateStudent">
        <input type="hidden" name="studentNumber" value="${param.studentNumber}">
        <label>Name:</label>
        <input type="text" name="name" value="${param.name}" required>
        <label>Surname:</label>
        <input type="text" name="surname" value="${param.surname}" required>
        <label>Score:</label>
        <input type="number" name="score" step="0.1" value="${param.score}" required>
        <button type="submit">Update</button>
        <a href="dashboard.jsp" class="button">Back to Dashboard</a>
    </form>
    
</body>
</html>