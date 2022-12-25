package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Application;
import com.example.server.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EditApplicationView {
    public Label studentName;
    public TextArea applicationBody;
    public CheckBox status;

    private Stage stage;
    private Parent root;

    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/client/applications-table-view.fxml")));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        data.getConnection().writeInt(9);
        data.getConnection().writeObject(data.getEditedApplication().getApplicationBody());
        Application application = (Application) data.getConnection().getObject();

        data.getConnection().writeInt(5);
        data.getConnection().writeLine("Student");
        data.getConnection().writeObject(application.getIdStudent());

        Student student = (Student) data.getConnection().getObject();
        studentName.setText(student.getName());
        applicationBody.setText(application.getBody());
        status.setSelected(application.isStatus());

    }

    public void saveChanges(ActionEvent event) throws IOException, ClassNotFoundException {
        Data data = Data.getInstance();
        data.getConnection().writeInt(9);
        data.getConnection().writeLine(data.getEditedApplication().getApplicationBody());
        Application applicationForID = (Application) data.getConnection().getObject();

        Application application = new Application(applicationForID.getIdApplication(), applicationBody.getText(), status.isSelected(),applicationForID.getIdStudent());

        data.getConnection().writeInt(3);
        data.getConnection().writeLine("Application");
        data.getConnection().writeObject(application);
        data.setEditedApplication(null);
        goBack(event);

    }
}
