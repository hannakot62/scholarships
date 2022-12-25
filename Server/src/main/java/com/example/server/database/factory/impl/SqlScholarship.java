package com.example.server.database.factory.impl;

import com.example.server.database.MyDatabase;
import com.example.server.database.factory.IScholarship;
import com.example.server.models.Scholarship;

import java.sql.SQLException;
import java.util.ArrayList;

public class SqlScholarship implements IScholarship {

    private final MyDatabase database;

    public SqlScholarship() throws SQLException {
        database = MyDatabase.getInstance();
    }

    @Override
    public int insert(Scholarship scholarship) {
        int science = scholarship.isScience()?1:0;
        int art = scholarship.isArt()?1:0;
        int orgActivity = scholarship.isOrgActivity()?1:0;
        int sport = scholarship.isSport()?1:0;

        String str = "INSERT INTO scholarship (idStudent, scholarshipValue, baseValue, attendanceC," +
        "scienceC, artC, sportC, orgActC, GPA, attendance, science," +
                "art, orgActivity, sport) VALUES('"
                + scholarship.getIdStudent() + "','" + scholarship.getScholarshipValue() + "','"
                + scholarship.getBaseValue()+ "','"+ scholarship.getAttendanceC()+ "','"+ scholarship.getScienceC()
                + "','" + scholarship.getArtC()+ "','"+scholarship.getSportC()+ "','"+scholarship.getOrgActC()+ "','"
                +scholarship.getGPA()+ "','"+scholarship.getAttendance()+ "','"+ science+ "','"
                +art+ "','"+orgActivity+ "','"+sport+ "')";
        ArrayList<String[]> result = database.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }
    @Override
    public ArrayList<Scholarship> selectAll() {
        String query = "SELECT * FROM scholarship";
        return getAll(query);    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Scholarship scholarship){

            int science = scholarship.isScience()?1:0;
            int art = scholarship.isArt()?1:0;
            int orgActivity = scholarship.isOrgActivity()?1:0;
            int sport = scholarship.isSport()?1:0;

            System.out.println(scholarship + "scholarship in update");

            String str = "UPDATE scholarship SET scholarshipValue="
                    + scholarship.getScholarshipValue()
                    + ", baseValue="
                    + scholarship.getBaseValue()
                    + ", attendanceC="
                    + scholarship.getAttendanceC()
                    + ", scienceC="
                    + scholarship.getScienceC()
                    + ", artC="
                    + scholarship.getArtC()
                    + ", orgActC="
                    + scholarship.getOrgActC()
                    + ", GPA="
                    + scholarship.getGPA()
                    + ", attendance="
                    + scholarship.getAttendance()
                    + ", science="
                    + science
                    + ", art="
                    + art
                    + ", orgActivity="
                    + orgActivity
                    + ", sport="
                    + sport
                    + "  WHERE idStudent=" + scholarship.getIdStudent();

            database.update(str);

    }

    @Override
    public Scholarship selectByStudentId(int idStudent) {
        String query = "SELECT * FROM scholarship WHERE idStudent=" + idStudent;
        return getOne(query);    }


    private ArrayList<Scholarship> getAll(String str){
        ArrayList<String[]> result = database.select(str);
        ArrayList<Scholarship> scholarships = new ArrayList<>();
        for (String[] items: result){
            Scholarship scholarship = new Scholarship();
            scholarship.setIdStudent(Integer.parseInt(items[0]));
            scholarship.setScholarshipValue(Double.parseDouble(items[1]));
            scholarship.setBaseValue(Double.parseDouble(items[2]));
            scholarship.setAttendanceC(Double.parseDouble(items[3]));
            scholarship.setScienceC(Double.parseDouble(items[4]));
            scholarship.setArtC(Double.parseDouble(items[5]));
            scholarship.setSportC(Double.parseDouble(items[6]));
            scholarship.setOrgActC(Double.parseDouble(items[7]));
            scholarship.setGPA(Double.parseDouble(items[8]));
            scholarship.setAttendance(Double.parseDouble(items[9]));
            scholarship.setScience(Integer.parseInt(items[10]) == 1);
            scholarship.setArt(Integer.parseInt(items[11]) == 1);
            scholarship.setOrgActivity(Integer.parseInt(items[12]) == 1);
            scholarship.setSport(Integer.parseInt(items[13]) == 1);

            scholarships.add(scholarship);
        }
        return scholarships;
    }

    private Scholarship getOne(String str){
        ArrayList<String[]> result = database.select(str);
        Scholarship scholarship = new Scholarship();
        for (String[] items: result){
            scholarship.setIdStudent(Integer.parseInt(items[0]));
            scholarship.setScholarshipValue(Double.parseDouble(items[1]));
            scholarship.setBaseValue(Double.parseDouble(items[2]));
            scholarship.setAttendanceC(Double.parseDouble(items[3]));
            scholarship.setScienceC(Double.parseDouble(items[4]));
            scholarship.setArtC(Double.parseDouble(items[5]));
            scholarship.setSportC(Double.parseDouble(items[6]));
            scholarship.setOrgActC(Double.parseDouble(items[7]));
            scholarship.setGPA(Double.parseDouble(items[8]));
            scholarship.setAttendance(Double.parseDouble(items[9]));
            scholarship.setScience(Integer.parseInt(items[10]) == 1);
            scholarship.setArt(Integer.parseInt(items[11]) == 1);
            scholarship.setOrgActivity(Integer.parseInt(items[12]) == 1);
            scholarship.setSport(Integer.parseInt(items[13]) == 1);
        }
        return scholarship;
    }
}
