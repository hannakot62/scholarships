package com.example.server.models;

import java.io.Serializable;
import java.util.Objects;

public class Application  implements Serializable {
    private int idApplication;
    private String body;
    private boolean status;
    private int idStudent;

    public Application() {
        this.idApplication = 0;
        this.body = "";
        this.status = false;
        this.idStudent = 0;
    }
    public Application( String body, boolean status, int idStudent) {
        this.body = body;
        this.status = status;
        this.idStudent = idStudent;
        this.idApplication=0;
    }
    public Application(int idApplication, String body, boolean status, int idStudent) {
        this.idApplication = idApplication;
        this.body = body;
        this.status = status;
        this.idStudent = idStudent;
    }

    public int getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(int idApplication) {
        this.idApplication = idApplication;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public String toString() {
        return "Application{" +
                "idApplication=" + idApplication +
                ", body='" + body + '\'' +
                ", status=" + status +
                ", idStudent=" + idStudent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return idApplication == that.idApplication && status == that.status && idStudent == that.idStudent && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApplication, body, status, idStudent);
    }
}
