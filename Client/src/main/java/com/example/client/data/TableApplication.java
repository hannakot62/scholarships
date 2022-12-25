package com.example.client.data;

public class TableApplication {
    private String studentName;
    private String applicationBody;
    private boolean status;

    public TableApplication(String studentName, String applicationBody, boolean status) {
        this.studentName = studentName;
        this.applicationBody = applicationBody;
        this.status = status;
    }

    public TableApplication() {
        this.studentName = "";
        this.applicationBody = "";
        this.status = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getApplicationBody() {
        return applicationBody;
    }

    public void setApplicationBody(String applicationBody) {
        this.applicationBody = applicationBody;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
