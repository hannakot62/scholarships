package com.example.server.models;

import java.io.Serializable;
import java.util.Objects;

public class Scholarship implements Serializable {
    private int idStudent;
    private double scholarshipValue;
    private double baseValue;
    private  double attendanceC;
    private double scienceC;
    private double artC;
    private double sportC;
    private  double orgActC;
    private double GPA;
    private double attendance;
    private boolean science;
    private boolean art;
    private boolean orgActivity;
    private boolean sport;

    public Scholarship(int idStudent, double scholarshipValue, double baseValue, double attendanceC, double scienceC, double artC, double sportC, double orgActC, double GPA, double attendance, boolean science, boolean art, boolean orgActivity, boolean sport) {
        this.idStudent = idStudent;
        this.scholarshipValue = scholarshipValue;
        this.baseValue = baseValue;
        this.attendanceC = attendanceC;
        this.scienceC = scienceC;
        this.artC = artC;
        this.sportC = sportC;
        this.orgActC = orgActC;
        this.GPA = GPA;
        this.attendance = attendance;
        this.science = science;
        this.art = art;
        this.orgActivity = orgActivity;
        this.sport = sport;
    }

    public Scholarship() {
        this.idStudent = 0;
        this.scholarshipValue = 0;
        this.baseValue = 0;
        this.attendanceC = 0;
        this.scienceC = 0;
        this.artC = 0;
        this.sportC = 0;
        this.orgActC = 0;
        this.GPA = 0;
        this.attendance = 0;
        this.science = false;
        this.art = false;
        this.orgActivity = false;
        this.sport = false;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public double getScholarshipValue() {
        return scholarshipValue;
    }

    public void setScholarshipValue(double scholarshipValue) {
        this.scholarshipValue = scholarshipValue;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double getAttendanceC() {
        return attendanceC;
    }

    public void setAttendanceC(double attendanceC) {
        this.attendanceC = attendanceC;
    }

    public double getScienceC() {
        return scienceC;
    }

    public void setScienceC(double scienceC) {
        this.scienceC = scienceC;
    }

    public double getArtC() {
        return artC;
    }

    public void setArtC(double artC) {
        this.artC = artC;
    }

    public double getSportC() {
        return sportC;
    }

    public void setSportC(double sportC) {
        this.sportC = sportC;
    }

    public double getOrgActC() {
        return orgActC;
    }

    public void setOrgActC(double orgActC) {
        this.orgActC = orgActC;
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

    public boolean isScience() {
        return science;
    }

    public void setScience(boolean science) {
        this.science = science;
    }

    public boolean isArt() {
        return art;
    }

    public void setArt(boolean art) {
        this.art = art;
    }

    public boolean isOrgActivity() {
        return orgActivity;
    }

    public void setOrgActivity(boolean orgActivity) {
        this.orgActivity = orgActivity;
    }

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scholarship that = (Scholarship) o;
        return idStudent == that.idStudent && Double.compare(that.scholarshipValue, scholarshipValue) == 0 && Double.compare(that.baseValue, baseValue) == 0 && Double.compare(that.attendanceC, attendanceC) == 0 && Double.compare(that.scienceC, scienceC) == 0 && Double.compare(that.artC, artC) == 0 && Double.compare(that.sportC, sportC) == 0 && Double.compare(that.orgActC, orgActC) == 0 && Double.compare(that.GPA, GPA) == 0 && Double.compare(that.attendance, attendance) == 0 && science == that.science && art == that.art && orgActivity == that.orgActivity && sport == that.sport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, scholarshipValue, baseValue, attendanceC, scienceC, artC, sportC, orgActC, GPA, attendance, science, art, orgActivity, sport);
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "idStudent=" + idStudent +
                ", scholarshipValue=" + scholarshipValue +
                ", baseValue=" + baseValue +
                ", attendanceC=" + attendanceC +
                ", scienceC=" + scienceC +
                ", artC=" + artC +
                ", sportC=" + sportC +
                ", orgActC=" + orgActC +
                ", GPA=" + GPA +
                ", attendance=" + attendance +
                ", science=" + science +
                ", art=" + art +
                ", orgActivity=" + orgActivity +
                ", sport=" + sport +
                '}';
    }
}
