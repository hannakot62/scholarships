package com.example.server.database.factory;

import com.example.server.models.User;

import java.util.ArrayList;

public interface IUser{
    int insert(User user);
    User selectUser(String login, String password);
    ArrayList<User> selectAll();
    void delete(int id);
    void update(User user, int id);

    User selectById(int id);

}
