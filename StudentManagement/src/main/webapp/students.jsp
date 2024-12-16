<!-- students.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>All Students</h1>
    <table>
        <tr>
            <th>Student Number</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Score</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.studentNumber}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>${student.score}</td>
                <td>
                    <form action="StudentManagementServlet" method="post"">
                        <input type="hidden" name="action" value="deleteStudent">
                        <input type="hidden" name="studentNumber" value="${student.studentNumber}">
                        <button type="submit">Delete</button>
                    </form>
                    <form action="updateStudent.jsp" method="get"">
                        <input type="hidden" name="studentNumber" value="${student.studentNumber}">
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <a href="dashboard.jsp" class="button">Back to Dashboard</a>
    </table>
    
</body>
</html>