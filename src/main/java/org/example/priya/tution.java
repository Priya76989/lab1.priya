package org.example.priya; // Ensure the package matches your structure

public class tution {
    private int id;
    private String student;
    private String teacher;
    private int className;

    public tution(int id, String student, String teacher, int className) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.className = className;
    }

    // Getters
    public int getId() { return id; }
    public String getStudent() { return student; }
    public String getTeacher() { return teacher; }
    public int getClassName() { return className; }
}
