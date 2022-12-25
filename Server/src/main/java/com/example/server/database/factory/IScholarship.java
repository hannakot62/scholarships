package com.example.server.database.factory;

import com.example.server.models.Scholarship;

import java.util.ArrayList;

public interface IScholarship {
    int insert(Scholarship scholarship);
    ArrayList<Scholarship> selectAll();
    void delete(int id);
    void update(Scholarship scholarship);
    Scholarship selectByStudentId(int id);

}
