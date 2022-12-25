package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Scholarship;
import com.example.server.models.ScholarshipCoefficients;
import com.example.server.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EditStudentView {
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

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        data.getConnection().writeInt(8);
        data.getConnection().writeObject(data.getEditedStudent().getName());
        Student student = (Student) data.getConnection().getObject();
        System.out.println(student);


        name.setText(student.getName());
        sport.setSelected(student.isSport());
        art.setSelected(student.isArt());
        science.setSelected(student.isScience());
        orgActivity.setSelected(student.isOrgActivity());


        switch (student.getBudgetForm()) {
            case "paid": {
                paid.setSelected(true);
                budget.setSelected(false);
                break;
            }
            case "budget": {
                budget.setSelected(true);
                paid.setSelected(false);
                break;
            }
        }
        gpa.setText(Double.toString(student.getGPA()));
        attendance.setText(Double.toString(student.getAttendance()));

    }
    public void saveChanges(ActionEvent event) throws IOException, ClassNotFoundException {

        Data data = Data.getInstance();
        data.getConnection().writeInt(8);
        data.getConnection().writeObject(data.getEditedStudent().getName());
        Student studentForID = (Student) data.getConnection().getObject();

        String budgetForm = budget.isSelected()? "budget": paid.isSelected()?"paid":"";
        Student student = new Student(studentForID.getIdStudent(), name.getText(), budgetForm, Double.parseDouble(gpa.getText()),
                Double.parseDouble(attendance.getText()), sport.isSelected(), art.isSelected(),
                science.isSelected(), orgActivity.isSelected());

        data.getConnection().writeInt(3);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(student);

        System.out.println(student+ "edited <----");
//        int id = (int) data.getConnection().getObject();

        data.getConnection().writeInt(4);
        data.getConnection().writeLine("ScholarshipCoefficients");
        ArrayList<ScholarshipCoefficients> coefs = (ArrayList<ScholarshipCoefficients>) data.getConnection().getObject();
        ScholarshipCoefficients coefsUsed = coefs.get(0);

        scholarship = calculate(coefsUsed, student);

        Scholarship studScholarship = new Scholarship(student.getIdStudent(), scholarship, coefsUsed.getBaseValue(),
                coefsUsed.getAttendanceC(), coefsUsed.getScienceC(), coefsUsed.getArtC(),
                coefsUsed.getSportC(), coefsUsed.getOrgActivityC(), student.getGPA(),
                student.getAttendance(), student.isScience(),
                student.isArt(), student.isOrgActivity(), student.isSport());

        System.out.println(studScholarship+"in edit view");
        data.getConnection().writeInt(3);
        data.getConnection().writeLine("Scholarship");
        data.getConnection().writeObject(studScholarship);
//        int idd = (int) data.getConnection().getObject();
        data.setEditedStudent(null);
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
