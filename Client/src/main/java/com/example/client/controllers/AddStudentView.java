package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Scholarship;
import com.example.server.models.ScholarshipCoefficients;
import com.example.server.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AddStudentView {
    public TextField name;
    public CheckBox sport;
    public CheckBox art;
    public CheckBox science;
    public CheckBox orgActivity;
    public RadioButton budget;
    public RadioButton paid;
    public TextField gpa;
    public TextField attendance;

    private double scholarship;

    private Stage stage;
    private Parent root;

    public void goBack(ActionEvent event) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/client/students-table-view.fxml")));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void addStudent(ActionEvent event) throws IOException, ClassNotFoundException {

        Data data = Data.getInstance();

        String budgetForm = budget.isSelected()? "budget": paid.isSelected()?"paid":"";
        Student student = new Student(name.getText(), budgetForm, Double.parseDouble(gpa.getText()),
                Double.parseDouble(attendance.getText()), sport.isSelected(), art.isSelected(),
                science.isSelected(), orgActivity.isSelected());

        data.getConnection().writeInt(2);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(student);
        int id = (int) data.getConnection().getObject();

        data.getConnection().writeInt(4);
        data.getConnection().writeLine("ScholarshipCoefficients");
        ArrayList<ScholarshipCoefficients> coefs = (ArrayList<ScholarshipCoefficients>) data.getConnection().getObject();
        ScholarshipCoefficients coefsUsed = coefs.get(0);

        scholarship = calculate(coefsUsed, student);

        Scholarship studScholarship = new Scholarship(id, scholarship, coefsUsed.getBaseValue(),
                coefsUsed.getAttendanceC(), coefsUsed.getScienceC(), coefsUsed.getArtC(),
                coefsUsed.getSportC(), coefsUsed.getOrgActivityC(), student.getGPA(),
                student.getAttendance(), student.isScience(),
                student.isArt(), student.isOrgActivity(), student.isSport());

        data.getConnection().writeInt(2);
        data.getConnection().writeLine("Scholarship");
        data.getConnection().writeObject(studScholarship);
        int idd = (int) data.getConnection().getObject();
        goBack(event);
    }

private double calculate (ScholarshipCoefficients coefs, Student student){
        int art = student.isArt()?1:0;
        int sport = student.isSport()?1:0;
        int science = student.isScience()?1:0;
        int org = student.isOrgActivity()?1:0;

        double scholarship = 0;
        scholarship = coefs.getBaseValue()*student.getGPA()/2+
                coefs.getBaseValue()*student.getAttendance()*coefs.getAttendanceC()+
                coefs.getBaseValue()*coefs.getArtC()*art+
                coefs.getBaseValue()*coefs.getOrgActivityC()*org+
                coefs.getBaseValue()*coefs.getSportC()*sport+
                coefs.getBaseValue()*coefs.getScienceC()*science;
        return scholarship;
}

}
