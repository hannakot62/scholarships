package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.client.data.TableApplication;
import com.example.server.models.Application;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationsTableView {
    private Stage stage;
    private Data data;
    private ArrayList<TableApplication> list;


    public TableView<TableApplication> applicationsTable;
    public TableColumn<TableApplication, String> fieldStudentName;
    public TableColumn<TableApplication, String> fieldApplicationBody;
    public TableColumn<TableApplication, Boolean> fieldStatus;

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/admin-page-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void editApplication(ActionEvent event) throws IOException{
        Data data = Data.getInstance();
        if (data.getEditedApplication() != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/edit-application-view.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {//??????????
        }
    }
    public void deleteApplication(ActionEvent event) throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        data.getConnection().writeInt(9);
        data.getConnection().writeObject(data.getEditedApplication().getApplicationBody());
        Application applicationForID = (Application) data.getConnection().getObject();
System.out.println(applicationForID);
        data.getConnection().writeInt(7);
        data.getConnection().writeLine("Application");
        data.getConnection().writeObject(applicationForID.getIdApplication());
        refreshTable();
    }

    private void refreshTable() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        data.getConnection().writeInt(4);
        data.getConnection().writeLine("Application");
        ArrayList<Application> applications = (ArrayList<Application>) data.getConnection().getObject();
        list = new ArrayList<>();
        for (Application application :
                applications) {
            data.getConnection().writeInt(5);
            data.getConnection().writeLine("Student");
            data.getConnection().writeObject(application.getIdStudent());
            Student student = (Student) data.getConnection().getObject();
            TableApplication tableApplication = new TableApplication(student.getName(), application.getBody(), application.isStatus());
            list.add(tableApplication);
        }

        fieldStudentName.setCellValueFactory(new PropertyValueFactory<TableApplication, String>("studentName"));
        fieldApplicationBody.setCellValueFactory(new PropertyValueFactory<TableApplication, String>("applicationBody"));
        fieldStatus.setCellValueFactory(new PropertyValueFactory<TableApplication, Boolean>("status"));

        ObservableList<TableApplication> arrayList = FXCollections.observableArrayList(list);

        applicationsTable.setItems(arrayList);
        data.setEditedApplication(null);
    }
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        refreshTable();

        TableView.TableViewSelectionModel<TableApplication> selectionModel = applicationsTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<TableApplication>() {

            @Override
            public void changed(ObservableValue<? extends TableApplication> observableValue, TableApplication oldVal, TableApplication newVal) {
                if (newVal != null) {
                    System.out.println(newVal.getStudentName());
                    data.setEditedApplication(newVal);
                }
            }
        });
    }

}
