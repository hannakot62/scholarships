package com.example.client.data;

import com.example.client.Connection;
import com.example.server.models.User;

public class Data {
    private User user;
    private Connection connection;
    private TableStudent editedStudent;
    private TableApplication editedApplication;

    private static Data instance;


    Data(){
        this.user= null;
        this.connection=null;
        this.editedStudent = null;
        this.editedApplication =null;
    }

    public static Data getInstance() {
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TableStudent getEditedStudent() {
        return editedStudent;
    }

    public void setEditedStudent(TableStudent editedStudent) {
        this.editedStudent = editedStudent;
    }

    public TableApplication getEditedApplication() {
        return editedApplication;
    }

    public void setEditedApplication(TableApplication editedApplication) {
        this.editedApplication = editedApplication;
    }
}
