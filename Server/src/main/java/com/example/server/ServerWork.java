package com.example.server;

import com.example.server.database.MyDatabase;
import com.example.server.database.factory.*;
import com.example.server.database.factory.impl.*;
import com.example.server.models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerWork {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private MyDatabase database;

    public ServerWork(ObjectOutputStream out, ObjectInputStream in, MyDatabase database) {
        this.out = out;
        this.in = in;
        this.database = database;
    }


    public void getOperationId(int id) throws SQLException, IOException, ClassNotFoundException {
        switch (id){
            case 1:
                signIn();
                break;
            case 2:
                saveEntityAndGetId();
                break;
            case 3:
                updateEntity();
                break;
            case 4:
                getAllEntities();
                break;
            case 5:
                getEntityById();
                break;
            case 6:
                searchEntity();
                break;
            case 7:
                deleteEntityById();
                break;
            case 8:
                findStudentByName();
                break;
            case 9:
                findApplicationByBody();
                break;
            default:
                break;
        }
    }

    private void findApplicationByBody() throws IOException, ClassNotFoundException, SQLException {
        String body = (String) in.readObject();
        IApplication iApplication = new SqlApplication();
        Application application = iApplication.selectApplicationByBody(body);
        out.writeObject(application);
    }

    private void findStudentByName() throws IOException, ClassNotFoundException, SQLException {
        String name = (String) in.readObject();
        IStudent iStudent = new SqlStudent();
        Student student = iStudent.selectStudentByName(name);
        out.writeObject(student);
    }

    private void signIn() throws IOException, SQLException, ClassNotFoundException {

        String login = (String) in.readObject();
        String password = (String) in.readObject();
        IUser iUser = new SqlUser();

        User user = iUser.selectUser(login, password);
        out.writeObject(user);
    }

        private void updateEntity() throws IOException, ClassNotFoundException, SQLException {
            String type = (String) in.readObject();
            switch (type) {
                case "Student": {
                    Student student = (Student) in.readObject();
                    IStudent iStudent = new SqlStudent();
                    iStudent.update(student);
                    break;
                }
                case "Scholarship": {
                    Scholarship scholarship = (Scholarship) in.readObject();
                    IScholarship iScholarship = new SqlScholarship();
                    iScholarship.update(scholarship);
                    break;
                }
                case "Application": {
                    Application application = (Application) in.readObject();
                    IApplication iApplication = new SqlApplication();
                    iApplication.update(application);
                    break;
                }
            }
        }

    private void saveEntityAndGetId() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        switch (type) {
            case "User": {
                User user = (User) in.readObject();
                IUser iUser = new SqlUser();
                int id = iUser.insert(user);
                out.writeObject(id);
                break;
            }
            case "Application":{
                Application application = (Application) in.readObject();
                IApplication iApplication = new SqlApplication();
                int id = iApplication.insert(application);
                out.writeObject(id);
                break;
            }
            case "Student":{
                Student student = (Student) in.readObject();
                IStudent iStudent = new SqlStudent();
                int id = iStudent.insert(student);
                out.writeObject(id);

                break;
            }
            case "Scholarship":{
                Scholarship scholarship = (Scholarship) in.readObject();
                IScholarship iScholarship = new SqlScholarship();
                int id = iScholarship.insert(scholarship);
                out.writeObject(id);

                break;
            }
        }
    }

    private void getAllEntities() throws SQLException, ClassNotFoundException, IOException {
        String type = (String) in.readObject();
        switch (type) {
            case "User": {
                IUser iUser = new SqlUser();
                ArrayList<User> list = iUser.selectAll();
                out.writeObject(list);
                break;
            }
            case "Student": {
                IStudent iStudent = new SqlStudent();
                ArrayList<Student> list = iStudent.selectAll();
                out.writeObject(list);
                break;
            }
            case "ScholarshipCoefficients":{
                IScholarshipCoefficients iScholarshipCoefficients = new SqlScholarshipCoefficients();
                ArrayList<ScholarshipCoefficients> list = iScholarshipCoefficients.selectAll();
                out.writeObject(list);
                break;
            }
            case "Application": {
                IApplication iApplication = new SqlApplication();
                ArrayList<Application> list = iApplication.selectAll();
                out.writeObject(list);
                break;
            }
            case "Scholarship": {
                IScholarship iScholarship = new SqlScholarship();
                ArrayList<Scholarship> list = iScholarship.selectAll();
                out.writeObject(list);
                break;
            }
        }
    }



    private void deleteEntityById() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        int id = (int) in.readObject();
        switch (type) {
            case "User": {
                IUser iUser = new SqlUser();
                iUser.delete(id);
                break;
            }
            case "Student": {
                IStudent iStudent = new SqlStudent();
                iStudent.delete(id);
                break;
            }
            case "Application": {
                IApplication iApplication = new SqlApplication();
                iApplication.delete(id);
                break;
            }
        }
    }

    private void searchEntity() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        switch (type) {
            case "Student": {
                String name = (String) in.readObject();
                IStudent iStudent = new SqlStudent();
                out.writeObject(iStudent.searchStudentByName(name));
                break;
            }
        }
    }


    private void getEntityById() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        switch (type) {
            case "User": {
                int id = (int) in.readObject();
                IUser iUser = new SqlUser();
                User user = iUser.selectById(id);
                out.writeObject(user);
                break;
            }
            case "Student":{
                int id = (int) in.readObject();
                IStudent iStudent = new SqlStudent();
                Student student = iStudent.selectStudentById(id);
                out.writeObject(student);
                break;
            }
            case "Scholarship":{
                int id = (int) in.readObject();
                IScholarship iScholarship = new SqlScholarship();
                Scholarship scholarship = iScholarship.selectByStudentId(id);
                out.writeObject(scholarship);
                break;
            }
        }
    }


}
