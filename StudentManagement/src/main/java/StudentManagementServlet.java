import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentManagementServlet")
public class StudentManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Sercan\\student.db";

    @Override
    public void init() throws ServletException {
        super.init();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {

            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                      "username TEXT UNIQUE NOT NULL, " +
                                      "password TEXT NOT NULL)";

            String createStudentTable = "CREATE TABLE IF NOT EXISTS students (" +
                                         "student_number INTEGER PRIMARY KEY, " +
                                         "name TEXT NOT NULL, " +
                                         "surname TEXT NOT NULL, " +
                                         "score REAL NOT NULL)";

            stmt.execute(createUserTable);
            stmt.execute(createStudentTable);
        } catch (Exception e) {
            throw new ServletException("Database initialization failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action + "dopost");
        try {
            switch (action) {
                case "signUp":
                    signUp(request, response);
                    break;
                case "signIn":
                    signIn(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "addStudent":
                    addStudent(request, response);
                    break;
                case "deleteStudent":
                    deleteStudent(request, response);
                    break;
                case "updateStudent":
                    updateStudent(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "listStudents":
                    listStudents(request, response);
                    break;
                case "listTop10Students":
                    listTop10Students(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username + " " + password);


        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            response.sendRedirect("index.jsp?message=Sign up successful");
            System.out.println("sign up successful");

        } catch (Exception e) {
        	System.out.println("sign up failed" + e.getMessage());
            response.sendRedirect("index.jsp?error=Sign up failed");
        }
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username + " " + password);

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("index.jsp?error=Invalid credentials");
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double score = Double.parseDouble(request.getParameter("score"));

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO students (student_number, name, surname, score) VALUES (?, ?, ?, ?)");) {

            ps.setInt(1, studentNumber);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setDouble(4, score);
            ps.executeUpdate();
            response.sendRedirect("dashboard.jsp?message=Student added successfully");
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE student_number = ?");) {

            ps.setInt(1, studentNumber);
            ps.executeUpdate();
            response.sendRedirect("dashboard.jsp?message=Student deleted successfully");
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double score = Double.parseDouble(request.getParameter("score"));

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("UPDATE students SET name = ?, surname = ?, score = ? WHERE student_number = ?");) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setDouble(3, score);
            ps.setInt(4, studentNumber);
            ps.executeUpdate();
            response.sendRedirect("dashboard.jsp?message=Student updated successfully");
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	List<Student> students = new ArrayList<>();
        // SQLite veritabanından öğrenci bilgilerini çekin

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students");) {

            while (rs.next()) {
            	String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                double score = rs.getDouble("score");
                students.add(new Student(studentNumber, name, surname, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }

    private void listTop10Students(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> topStudents = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY score DESC LIMIT 10");) {

        	while (rs.next()) {
            	String studentNumber = rs.getString("student_number");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                double score = rs.getDouble("score");
                topStudents.add(new Student(studentNumber, name, surname, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("topStudents", topStudents);
        request.getRequestDispatcher("topStudents.jsp").forward(request, response);
    }
}
