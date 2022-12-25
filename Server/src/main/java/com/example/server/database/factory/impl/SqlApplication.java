package com.example.server.database.factory.impl;

import com.example.server.database.MyDatabase;
import com.example.server.database.factory.IApplication;
import com.example.server.models.Application;

import java.sql.SQLException;
import java.util.ArrayList;

public class SqlApplication implements IApplication {
    private final MyDatabase database;

    public SqlApplication() throws SQLException {
        database = MyDatabase.getInstance();
    }

    @Override
    public int insert(Application application) {

        String status = application.isStatus()?"1":"0";
        String str = "INSERT INTO application  (body, status, idStudent) VALUES('"
     +  application.getBody() + "','"
                + status + "','" + application.getIdStudent() + "')";
        ArrayList<String[]> result = database.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public Application selectApplicationByBody(String body)
    {
        String query = "SELECT * FROM application WHERE body='" + body + "'";
        return getOne(query);    }

    @Override
    public ArrayList<Application> selectAll() {

        String query = "SELECT * FROM application";
        return getAll(query);
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM application WHERE idApplication = " + id;
        database.delete(str);
    }

    @Override
    public void update(Application application) {
        int status = application.isStatus()?1:0;
        System.out.println(application+"application in update");
        String str = "UPDATE application SET body='"
                + application.getBody() + "', status=" + status
        + "  WHERE idApplication=" + application.getIdApplication();
        database.update(str);

    }

    private ArrayList<Application> getAll(String str){
        ArrayList<String[]> result = database.select(str);
        ArrayList<Application> applications = new ArrayList<>();
        for (String[] items: result){
            Application application = new Application();
            application.setIdApplication(Integer.parseInt(items[0]));
            application.setBody(items[1]);
            application.setStatus(Integer.parseInt(items[2])==1);
            application.setIdStudent(Integer.parseInt(items[3]));
            applications.add(application);
        }
        return applications;
    }

    private Application getOne(String str){
        ArrayList<String[]> result = database.select(str);
        Application application = new Application();
        for (String[] items: result){
            application.setIdApplication(Integer.parseInt(items[0]));
            application.setBody(items[1]);
            application.setStatus(Integer.parseInt(items[2])==1);
            application.setIdStudent(Integer.parseInt(items[3]));
        }
        return application;
    }
}
