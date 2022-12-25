package com.example.client.controllers;

import com.example.client.data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageView {
    private Stage stage;

    public void goBack(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/auth-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Data data = Data.getInstance();
        data.setUser(null);
    }
    public void goToStudentsTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/students-table-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void goToApplications(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/applications-table-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void goToDiagram(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/diagram-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    }
