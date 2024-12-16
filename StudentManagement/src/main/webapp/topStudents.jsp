<!-- topStudents.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Top 10 Students</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Top 10 Students</h1>
    <table>
        <tr>
            <th>Student Number</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Score</th>
        </tr>
        <c:forEach var="student" items="${topStudents}">
            <tr>
                <td>${student.studentNumber}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>${student.score}</td>
            </tr>
        </c:forEach>
        <a href="dashboard.jsp" class="button">Back to Dashboard</a>
    </table>
    
</body>
</html>