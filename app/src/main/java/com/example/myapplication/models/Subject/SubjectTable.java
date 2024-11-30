package com.example.myapplication.models.Subject;

public class SubjectTable {
    private Long subjectID;
    private String subjectName;

    public SubjectTable() {
    }

    public SubjectTable(Long subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
