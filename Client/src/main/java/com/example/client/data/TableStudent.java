package com.example.client.data;

public class TableStudent {
    private String name;
    private String budgetForm;
    private double GPA;
    private double attendance;
    private boolean art;
    private boolean science;
    private boolean sport;
    private boolean orgActivity;
    private double scholarship;


    public TableStudent(String name, String budgetForm, double GPA, double attendance, boolean art, boolean science, boolean sport, boolean orgActivity, double scholarship) {
        this.name = name;
        this.budgetForm = budgetForm;
        this.GPA = GPA;
        this.attendance = attendance;
        this.art = art;
        this.science = science;
        this.sport = sport;
        this.orgActivity = orgActivity;
        this.scholarship = scholarship;
    }

    public TableStudent() {
        this.name = "";
        this.budgetForm = "";
        this.GPA = 0;
        this.attendance = 0;
        this.art = false;
        this.science = false;
        this.sport = false;
        this.orgActivity = false;
        this.scholarship = 0;
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

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    public boolean isOrgActivity() {
        return orgActivity;
    }

    public void setOrgActivity(boolean orgActivity) {
        this.orgActivity = orgActivity;
    }

    public double getScholarship() {
        return scholarship;
    }

    public void setScholarship(double scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public String toString() {
        return "TableStudent{" +
                "name='" + name + '\'' +
                ", budgetForm='" + budgetForm + '\'' +
                ", GPA=" + GPA +
                ", attendance=" + attendance +
                ", art=" + art +
                ", science=" + science +
                ", sport=" + sport +
                ", orgActivity=" + orgActivity +
                ", scholarship=" + scholarship +
                '}';
    }
}
