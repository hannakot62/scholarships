package com.example.server.database.factory.impl;

import com.example.server.database.MyDatabase;
import com.example.server.database.factory.IStudent;
import com.example.server.models.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class SqlStudent implements IStudent {

    private final MyDatabase database;

    public SqlStudent() throws SQLException {
        database = MyDatabase.getInstance();
    }

    @Override
    public ArrayList<Student> searchStudentByName(String name) {
        System.out.println(name);
        String query = "SELECT * FROM student WHERE name LIKE '%" + name + "%'";
        return getAll(query);
    }

    @Override
    public Student selectStudentByName(String name) {

            String query = "SELECT * FROM student WHERE name='" + name + "'";
            return getOne(query);
        }

    @Override
    public int insert(Student student){
    int science = student.isScience()?1:0;
    int art = student.isArt()?1:0;
    int orgActivity = student.isOrgActivity()?1:0;
    int sport = student.isSport()?1:0;
    String str = "INSERT INTO student ( name, budgetForm,GPA, attendance, sport, art, science, orgActivity)"
        +" VALUES ('" + student.getName() + "','"
            + student.getBudgetForm()+ "', "+ student.getGPA()+ ", "+ student.getAttendance()
            + ", "+ sport+ ","
            +art+ ","+science+ ","+orgActivity+ ")";
    ArrayList<String[]> result = database.insert(str);
        return Integer.parseInt(result.get(0)[0]);
}

    @Override
    public Student selectStudentById(int id) {
        String query = "SELECT * FROM student WHERE idStudent=" + id;
        return getOne(query);    }

    @Override
    public ArrayList<Student> selectAll() {
        String query = "SELECT * FROM student";
        return getAll(query);    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM student WHERE idStudent = " + id;
        database.delete(str);
    }

    @Override
    public void update(Student student) {
        int science = student.isScience()?1:0;
        int art = student.isArt()?1:0;
        int orgActivity = student.isOrgActivity()?1:0;
        int sport = student.isSport()?1:0;

            String str = "UPDATE student SET name='"
                    + student.getName()
                    + "', budgetForm='"
                    + student.getBudgetForm()
                    + "', GPA='"
                    + student.getGPA()
                    + "', attendance='"
                    + student.getAttendance()
                    + "', sport='"
                    + sport
                    + "', art='"
                    + art
                    + "', science='"
                    + science
                    + "', orgActivity='"
                    + orgActivity
                    + "'  WHERE idStudent=" + student.getIdStudent();

            database.update(str);
        }

    private ArrayList<Student> getAll(String str){
        ArrayList<String[]> result = database.select(str);
        ArrayList<Student> students = new ArrayList<>();
        for (String[] items: result){
            Student student = new Student();
            student.setIdStudent(Integer.parseInt(items[0]));
            student.setName(items[1]);
            student.setBudgetForm(items[2]);
            student.setGPA(Double.parseDouble(items[3]));
            student.setAttendance(Double.parseDouble(items[4]));
            student.setSport(Integer.parseInt(items[5]) == 1);
            student.setArt(Integer.parseInt(items[6]) == 1);
            student.setScience(Integer.parseInt(items[7]) == 1);
            student.setOrgActivity(Integer.parseInt(items[8]) == 1);

            students.add(student);
        }
        System.out.println(students+"students in getall");
        return students;
    }

    private Student getOne(String str){
        ArrayList<String[]> result = database.select(str);
        Student student = new Student();
        for (String[] items: result){
            student.setIdStudent(Integer.parseInt(items[0]));
            student.setName(items[1]);
            student.setBudgetForm(items[2]);
            student.setGPA(Double.parseDouble(items[3]));
            student.setAttendance(Double.parseDouble(items[4]));
            student.setSport(Integer.parseInt(items[5]) == 1);
            student.setArt(Integer.parseInt(items[6]) == 1);
            student.setScience(Integer.parseInt(items[7]) == 1);
            student.setOrgActivity(Integer.parseInt(items[8]) == 1);
        }
        return student;
    }

}
