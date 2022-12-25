package com.example.server.models;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
   private int idStudent;
   private String name;
   private String budgetForm;
   private double GPA;
   private double attendance;
   private boolean sport;
   private boolean art;
   private boolean science;
   private boolean orgActivity;

    public Student() {
        this.idStudent = 0;
        this.name = "";
        this.budgetForm = "paid";
        this.GPA = 0;
        this.attendance = 0;
        this.sport = false;
        this.art = false;
        this.science = false;
        this.orgActivity = false;
    }

    public Student(int idStudent, String name, String budgetForm, double GPA, double attendance, boolean sport, boolean art, boolean science, boolean orgActivity) {
        this.idStudent = idStudent;
        this.name = name;
        this.budgetForm = budgetForm;
        this.GPA = GPA;
        this.attendance = attendance;
        this.sport = sport;
        this.art = art;
        this.science = science;
        this.orgActivity = orgActivity;
    }
    public Student(String name, String budgetForm, double GPA, double attendance, boolean sport, boolean art, boolean science, boolean orgActivity) {
        this.name = name;
        this.budgetForm = budgetForm;
        this.GPA = GPA;
        this.attendance = attendance;
        this.sport = sport;
        this.art = art;
        this.science = science;
        this.orgActivity = orgActivity;
        this.idStudent=0;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudgetForm() {
        return budgetForm;
    }

    public void setBudgetForm(String budgetForm) {
        this.budgetForm = budgetForm;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    public boolean isArt() {
        return art;
    }

    public void setArt(boolean art) {
        this.art = art;
    }

    public boolean isScience() {
        return science;
    }

    public void setScience(boolean science) {
        this.science = science;
    }

    public boolean isOrgActivity() {
        return orgActivity;
    }

    public void setOrgActivity(boolean orgActivity) {
        this.orgActivity = orgActivity;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", name='" + name + '\'' +
                ", budgetForm='" + budgetForm + '\'' +
                ", GPA=" + GPA +
                ", attendance=" + attendance +
                ", sport=" + sport +
                ", art=" + art +
                ", science=" + science +
                ", orgActivity=" + orgActivity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return idStudent == student.idStudent && Double.compare(student.GPA, GPA) == 0 && Double.compare(student.attendance, attendance) == 0 && sport == student.sport && art == student.art && science == student.science && orgActivity == student.orgActivity && Objects.equals(name, student.name) && Objects.equals(budgetForm, student.budgetForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, name, budgetForm, GPA, attendance, sport, art, science, orgActivity);
    }
}
