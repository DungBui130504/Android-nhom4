package com.example.myapplication.models.Subject;

public class SubjectObject {
    public int subjectID;
    public String subjectName;

    public int userID;


    public SubjectObject(int subjectID, String subjectName ,int userID) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.userID = userID;
    }
    public SubjectObject( String subjectName) {
        this.subjectName = subjectName;
        this.userID = userID;
    }

    public SubjectObject() {
        this.subjectID = -1;
        this.subjectName = "none";
    }
}
