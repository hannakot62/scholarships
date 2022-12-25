package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Application;
import com.example.server.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationView {
    public TextArea applicationBody;

    private Stage stage;


    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/student-profile-view.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void sendApplication(ActionEvent event) throws IOException, ClassNotFoundException {

        Data data = Data.getInstance();
        User user = data.getUser();

        Application application = new Application(applicationBody.getText(), false, user.getId());

        data.getConnection().writeInt(2);
        data.getConnection().writeLine("Application");
        data.getConnection().writeObject(application);
        int id = (int) data.getConnection().getObject();

        goBack(event);
    }
}
