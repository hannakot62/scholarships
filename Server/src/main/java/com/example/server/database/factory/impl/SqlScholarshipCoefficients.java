package com.example.server.database.factory.impl;

import com.example.server.database.MyDatabase;
import com.example.server.database.factory.IScholarshipCoefficients;
import com.example.server.models.ScholarshipCoefficients;

import java.sql.SQLException;
import java.util.ArrayList;

public class SqlScholarshipCoefficients implements IScholarshipCoefficients {
    private final MyDatabase database;

    public SqlScholarshipCoefficients() throws SQLException {
        database = MyDatabase.getInstance();
    }

    @Override
    public ArrayList<ScholarshipCoefficients> selectAll() {
        String query = "SELECT * FROM scholarship_coefficients";
        return getAll(query);
    }

    private ArrayList<ScholarshipCoefficients> getAll(String str){
        ArrayList<String[]> result = database.select(str);
        ArrayList<ScholarshipCoefficients> scholarship_cs = new ArrayList<>();
        for (String[] items: result){
            ScholarshipCoefficients coefficients = new ScholarshipCoefficients();

            coefficients.setBaseValue(Double.parseDouble(items[0]));
            coefficients.setAttendanceC(Double.parseDouble(items[1]));
            coefficients.setScienceC(Double.parseDouble(items[2]));
            coefficients.setArtC(Double.parseDouble(items[3]));
            coefficients.setSportC(Double.parseDouble(items[4]));
            coefficients.setOrgActivityC(Double.parseDouble(items[5]));
            scholarship_cs.add(coefficients);
        }
        return scholarship_cs;
    }
}
