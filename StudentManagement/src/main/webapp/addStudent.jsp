<!-- addStudent.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Add New Student</h1>
    <form action="StudentManagementServlet" method="post">
        <input type="hidden" name="action" value="addStudent">

        <label for="studentNumber">Student Number:</label>
        <input type="text" id="studentNumber" name="studentNumber" required>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" required>

        <label for="score">Score:</label>
        <input type="number" id="score" name="score" step="0.1" required>

        <button type="submit">Add Student</button>
        
        <a href="dashboard.jsp" class="button">Back to Dashboard</a>
    </form>

    
</body>
</html>
