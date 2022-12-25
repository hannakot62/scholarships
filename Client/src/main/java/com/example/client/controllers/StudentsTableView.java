package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.client.data.TableStudent;
import com.example.server.models.Scholarship;
import com.example.server.models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StudentsTableView {
    private Stage stage;
    private Data data;
    private ArrayList<TableStudent> list;


    public TableView<TableStudent> studentsTable;
    public TableColumn<TableStudent, String> fieldName;
    public TableColumn<TableStudent, String> fieldBudgetForm;
    public TableColumn<TableStudent, Double> fieldGPA;
    public TableColumn<TableStudent, Double> fieldAttendance;
    public TableColumn<TableStudent, Boolean> fieldArt;
    public TableColumn<TableStudent, Boolean> fieldScience;
    public TableColumn<TableStudent, Boolean> fieldSport;
    public TableColumn<TableStudent, Boolean> fieldOrgActivity;
    public TableColumn<TableStudent, Double> fieldScholarship;

    public TextField search;

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/admin-page-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void addStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/add-student-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void editStudent(ActionEvent event) throws IOException{
        if (data.getEditedStudent() != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/edit-student-view.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {//??????????
        }

    }
    public void deleteStudent(ActionEvent event) throws IOException, ClassNotFoundException {
        data.getConnection().writeInt(8);
        data.getConnection().writeObject(data.getEditedStudent().getName());
        Student studentForID = (Student) data.getConnection().getObject();

        data.getConnection().writeInt(7);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(studentForID.getIdStudent());
        refreshTable();
    }
    private void refreshTable() throws IOException, ClassNotFoundException {
        data = Data.getInstance();
        data.getConnection().writeInt(4);
        data.getConnection().writeLine("Student");
        ArrayList<Student> students = (ArrayList<Student>) data.getConnection().getObject();
        list = new ArrayList<>();
        for (Student student :
                students) {
            data.getConnection().writeInt(5);
            data.getConnection().writeLine("Scholarship");
            data.getConnection().writeObject(student.getIdStudent());
            Scholarship scholarship = (Scholarship) data.getConnection().getObject();
            TableStudent tableStudent = new TableStudent(student.getName(), student.getBudgetForm(),
                    student.getGPA(), student.getAttendance(), student.isArt(), student.isScience(),
                    student.isSport(), student.isOrgActivity(), scholarship.getScholarshipValue());
                list.add(tableStudent);
        }

        fieldName.setCellValueFactory(new PropertyValueFactory<TableStudent, String>("name"));
        fieldBudgetForm.setCellValueFactory(new PropertyValueFactory<TableStudent, String>("budgetForm"));
        fieldGPA.setCellValueFactory(new PropertyValueFactory<TableStudent, Double>("GPA"));
        fieldAttendance.setCellValueFactory(new PropertyValueFactory<TableStudent, Double>("attendance"));
        fieldArt.setCellValueFactory(new PropertyValueFactory<TableStudent, Boolean>("art"));
        fieldScience.setCellValueFactory(new PropertyValueFactory<TableStudent, Boolean>("science"));
        fieldSport.setCellValueFactory(new PropertyValueFactory<TableStudent, Boolean>("sport"));
        fieldOrgActivity.setCellValueFactory(new PropertyValueFactory<TableStudent, Boolean>("orgActivity"));
        fieldScholarship.setCellValueFactory(new PropertyValueFactory<TableStudent, Double>("scholarship"));

        ObservableList<TableStudent> arrayList = FXCollections.observableArrayList(list);

        studentsTable.setItems(arrayList);
        data.setEditedStudent(null);
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        refreshTable();

        TableView.TableViewSelectionModel<TableStudent> selectionModel = studentsTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<TableStudent>() {

            @Override
            public void changed(ObservableValue<? extends TableStudent> observableValue, TableStudent oldVal, TableStudent newVal) {
                if (newVal != null) {
                    System.out.println(newVal.getName());
                    data.setEditedStudent(newVal);
                }
            }
        });

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                if (!search.getText().equals("")) {

                    try {
                        data.getConnection().writeObject(6);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.getConnection().writeObject("Student");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.getConnection().writeObject(search.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    list.clear();
                    ArrayList<Student> students = null;
                    try {
                        students = (ArrayList<Student>) data.getConnection().getObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Student student :
                            students) {
                        try {
                            data.getConnection().writeInt(5);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.getConnection().writeLine("Scholarship");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.getConnection().writeObject(student.getIdStudent());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scholarship scholarship = null;
                        try {
                            scholarship = (Scholarship) data.getConnection().getObject();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        TableStudent tableStudent =
                                new TableStudent(student.getName(),student.getBudgetForm(),student.getGPA(),
                                        student.getAttendance(),student.isArt(), student.isScience(), student.isSport(),
                                        student.isOrgActivity(), scholarship.getScholarshipValue() );
                        list.add(tableStudent);
                    }
                    ObservableList<TableStudent> arrayList = FXCollections.observableArrayList(list);
                    studentsTable.setItems(arrayList);

                }else{
                    try {
                        refreshTable();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }
}
