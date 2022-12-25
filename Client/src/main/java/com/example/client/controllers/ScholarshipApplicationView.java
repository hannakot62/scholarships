package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Application;
import com.example.server.models.Scholarship;
import com.example.server.models.Student;
import com.example.server.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ScholarshipApplicationView {
    public Label name;
    public CheckBox sport;
    public CheckBox art;
    public CheckBox science;
    public CheckBox orgActivity;
    public Label scholarship;
    public TextArea explanation;
    public TextField gpa;
    public  TextField attendance;
    public RadioButton budget;
    public RadioButton paid;


    private Stage stage;



    public void sendScholarshipApplication(ActionEvent event) throws IOException, ClassNotFoundException {

        Data data = Data.getInstance();
        User user = data.getUser();
        String budgetForm = budget.isSelected()? "бюджетная": paid.isSelected()?"платная":"";

        String applicationBody = "Спорт: "+ sport.isSelected() + ",\n"+
                "Творчество: "+ art.isSelected() + ",\n"+
                "Наука: "+ science.isSelected() + ",\n"+
                "Организационная деятельность: "+ orgActivity.isSelected() + ",\n"+
                "Средний балл: "+ gpa.getText()+ ",\n"+
                "Посещаемость: "+ attendance.getText() + ",\n"+
                "Форма обучения: "+ budgetForm+ ",\n"+
                "Пояснение: "+explanation.getText();
        Application application = new Application(applicationBody, false, user.getId());

        data.getConnection().writeInt(2);
        data.getConnection().writeLine("Application");
        data.getConnection().writeObject(application);
        int id = (int) data.getConnection().getObject();

goBack(event);

    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        User user = data.getUser();
        data.getConnection().writeInt(5);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(user.getId());
        Student student = (Student) data.getConnection().getObject();

        data.getConnection().writeInt(5);
        data.getConnection().writeLine("Scholarship");
        data.getConnection().writeObject(user.getId());
        Scholarship scholarshipObj = (Scholarship) data.getConnection().getObject();
        String sch = Double.toString(scholarshipObj.getScholarshipValue());


        name.setText(student.getName());
        sport.setSelected(student.isSport());
        art.setSelected(student.isArt());
        science.setSelected(student.isScience());
        orgActivity.setSelected(student.isOrgActivity());

        switch (student.getBudgetForm()) {
            case "paid": {
                paid.setSelected(true);
                break;
            }
            case "budget": {
                budget.setSelected(true);
                break;
            }
        }
        gpa.setText(Double.toString(student.getGPA()));
        attendance.setText((student.getAttendance()) + "%");
        scholarship.setText(sch + " б.р.");
    }
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/student-profile-view.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    }
