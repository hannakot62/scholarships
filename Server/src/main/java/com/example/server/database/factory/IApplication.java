package com.example.server.database.factory;

import com.example.server.models.Application;

import java.util.ArrayList;

public interface IApplication {
    int insert(Application application);
    Application selectApplicationByBody(String body);
    ArrayList<Application> selectAll();
    void delete(int id);
    void update(Application application);
}
