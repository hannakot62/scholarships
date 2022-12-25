package com.example.server.database.factory;

import com.example.server.models.Student;

import java.util.ArrayList;

public interface IStudent {
    int insert(Student student);
    Student selectStudentById(int id);
    ArrayList<Student> selectAll();
    void delete(int id);
    void update(Student student);
    Student selectStudentByName(String name);
     ArrayList<Student> searchStudentByName(String name);
}
