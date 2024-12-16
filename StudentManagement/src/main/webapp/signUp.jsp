<!-- index.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Welcome to Student Management</h1>
    <h2>New User? Sign Up</h2>
    <form action="StudentManagementServlet" method="post">
        <input type="hidden" name="action" value="signUp">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit">Sign Up</button>
        <a href="index.jsp" class="button">Back to Welcome Page</a>
    </form>
    
</body>
</html>