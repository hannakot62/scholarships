package com.example.client.controllers;

import com.example.client.data.Data;
import com.example.server.models.Scholarship;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DiagramView {
    public PieChart pieChart;
    public Button save;
    private ArrayList<String> pieColors = new ArrayList<>();

    private Stage stage;
    private DirectoryChooser directoryChooser;

    private int i = 0;
    private int first = 0, second = 0, third = 0, fourth = 0 , fifth=0;


    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        pieColors.add("#CBC5EA");
        pieColors.add("#654A4A");
        pieColors.add("#313D5A");
        pieColors.add("#73628A");
        pieColors.add("#183642");

        directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);


        Data data = Data.getInstance();
        data.getConnection().writeInt(4);
        data.getConnection().writeLine("Scholarship");
        ArrayList<Scholarship> scholarships = (ArrayList<Scholarship>) data.getConnection().getObject();

        for (Scholarship scholarship :
                scholarships) {
            if (scholarship.getScholarshipValue() >= 0 && scholarship.getScholarshipValue() <= 50) {
                first++;
            }
            else if (scholarship.getScholarshipValue() <= 100) {
                second++;
            }
            else if (scholarship.getScholarshipValue() <= 150) {
                third++;
            }
            else if (scholarship.getScholarshipValue() <= 200) {
                fourth++;
            }
            else if (scholarship.getScholarshipValue() <= 250) {
                fifth++;
            }
        }


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("0-50 б.р.", first),
                        new PieChart.Data("51-100 б.р.", second),
                        new PieChart.Data("101-150 б.р.", third),
                        new PieChart.Data("151-200 б.р.", fourth),
                        new PieChart.Data("201-250 б.р.", fifth));
        pieChartData.forEach(record ->
                record.nameProperty().bind(
                        Bindings.concat(
                                record.getName(), " - количество студентов: ", record.pieValueProperty()
                        )
                )
        );
        pieChart.getData().addAll(pieChartData);
        applyCustomColorSequence(pieChartData, pieColors);

    }

    private void applyCustomColorSequence(
            ObservableList<PieChart.Data> pieChartData,
            ArrayList<String> pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle(
                    "-fx-pie-color: " + pieColors.get(i) + ";"
            );
            i++;
        }
    }


    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client/admin-page-view.fxml"));
        Scene scene = new Scene(root);
        stage =(Stage) ((Parent)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle("Выберите путь расположения файла");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public void saveReport(ActionEvent event) {
        File dir = directoryChooser.showDialog(save.getScene().getWindow());
        if (dir != null) {
            String way = dir.getAbsolutePath() + "/report.txt";
            try (FileWriter writer = new FileWriter(way, false)) {
                writer.write(
                                "Размер стипендии|Количество студентов" + "\n" +
                                "-------------------------------------" + "\n" +
                                "0-50 б.р.       | " + first + "\n" +
                                "51-100 б.р.     | " + second + "\n" +
                                "101-150 б.р.    | " + third + "\n" +
                                "151-200 б.р.    | " + fourth + "\n" +
                                "201-250 б.р.    | " + fifth + "\n");

                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}