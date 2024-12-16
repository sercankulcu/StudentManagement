package com.example.model;

public class Student {
    private String studentNumber;
    private String name;
    private String surname;
    private double score;

    // Constructor
    public Student(String studentNumber, String name, String surname, double score) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    // Getter and Setter Methods
    public String getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getScore() {
        return score;
    }
}
