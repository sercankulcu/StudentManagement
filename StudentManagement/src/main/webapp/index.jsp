<!-- index.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Sign In / Sign Up</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Welcome to Student Management</h1>
    <form action="StudentManagementServlet" method="post">
        <input type="hidden" name="action" value="signIn">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit">Sign In</button>
        <a href="signUp.jsp" class="button">Sign Up</a>
    </form>
</body>
</html>