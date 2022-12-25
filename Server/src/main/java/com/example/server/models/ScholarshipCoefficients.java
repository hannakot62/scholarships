package com.example.server.models;

import java.io.Serializable;
import java.util.Objects;

public class ScholarshipCoefficients implements Serializable {

   private double baseValue;
    private double attendanceC;
    private double scienceC;
    private double artC;
    private double sportC;
    private double orgActivityC;

    public ScholarshipCoefficients() {
        this.baseValue = 30;
        this.attendanceC = 0;
        this.scienceC = 0;
        this.artC = 0;
        this.sportC = 0;
        this.orgActivityC = 0;
    }

    public ScholarshipCoefficients(double baseValue, double attendanceC, double scienceC, double artC, double sportC, double orgActivityC) {
        this.baseValue = baseValue;
        this.attendanceC = attendanceC;
        this.scienceC = scienceC;
        this.artC = artC;
        this.sportC = sportC;
        this.orgActivityC = orgActivityC;
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

    public double getOrgActivityC() {
        return orgActivityC;
    }

    public void setOrgActivityC(double orgActivityC) {
        this.orgActivityC = orgActivityC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, attendanceC, scienceC, artC, sportC, orgActivityC);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ScholarshipCoefficients sc = (ScholarshipCoefficients) obj;
        return Objects.equals(baseValue, sc.baseValue)
                && Objects.equals(attendanceC, sc.attendanceC)
                && Objects.equals(scienceC, sc.scienceC)
                && Objects.equals(artC, sc.artC)
                && Objects.equals(sportC, sc.sportC)
                && Objects.equals(orgActivityC, sc.orgActivityC);
    }

    @Override
    public String toString() {
        return "ScholarshipCoefficients{" +
                "baseValue=" + baseValue +
                ", attendanceC='" + attendanceC + '\'' +
                ", scienceC='" + scienceC + '\'' +
                ", artC='" + artC + '\'' +
                ", sportC='" + sportC + '\'' +
                ", orgActivityC='" + orgActivityC + '\'' +
                '}';

    }
}
