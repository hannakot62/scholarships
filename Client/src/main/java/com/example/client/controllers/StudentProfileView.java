package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Scholarship;
import com.example.server.models.Student;
import com.example.server.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentProfileView {
    public Label surnameName;
    public Label Fathername;
    public CheckBox sport;
    public CheckBox art;
    public CheckBox science;
    public CheckBox orgActivity;
    public Label budgetForm;
    public Label gpa;
    public Label attendance;
    public Label scholarship;

    private Stage stage;


    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        User user = data.getUser();
        data.getConnection().writeInt(5);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(user.getId());
        Student student = (Student) data.getConnection().getObject();

        data = Data.getInstance();
        data.getConnection().writeInt(5);
        data.getConnection().writeLine("Scholarship");
        data.getConnection().writeObject(user.getId());
        Scholarship scholarshipObj = (Scholarship) data.getConnection().getObject();
        String sch = Double.toString(scholarshipObj.getScholarshipValue());


        String fullName = student.getName();
        String[] names = fullName.split(" ");
        surnameName.setText(names[0] + " " + names[1]);
        Fathername.setText(names[2]);
        sport.setSelected(student.isSport());
        art.setSelected(student.isArt());
        science.setSelected(student.isScience());
        orgActivity.setSelected(student.isOrgActivity());


        switch (student.getBudgetForm()) {
            case "paid": {
                budgetForm.setText("платная");
                break;
            }
            case "budget": {
                budgetForm.setText("бюджетная");
                break;
            }
        }
        gpa.setText(Double.toString(student.getGPA()));
        attendance.setText(Double.toString(student.getAttendance()) + "%");
        scholarship.setText(sch + " б.р.");

    }

    public void goBack(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/auth-view.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        Data data = Data.getInstance();
        data.setUser(null);
    }

    public void addApplication(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/application-view.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    public void addScholarshipApplication(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/scholarship-application-view.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
