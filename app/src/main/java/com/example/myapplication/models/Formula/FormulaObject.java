package com.example.myapplication.models.Formula;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
public class FormulaObject {
    private Integer formulaID;
    private String formulaName;
    private String formula;
    private int userID;
    private Integer subjectID;
    private String updatedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public FormulaObject() {
        formulaID = -1;
        userID = -1;
        subjectID = -1;
        formula = "none";
        formulaName = "none";
        updatedDate = LocalDate.now().toString();
    }

    public FormulaObject(Integer formulaID, String formulaName, String formula, int userID, Integer subjectID, String updatedDate) {
        this.formulaID = formulaID;
        this.formulaName = formulaName;
        this.formula = formula;
        this.userID = userID;
        this.subjectID = subjectID;
        this.updatedDate = updatedDate;
    }

    public Integer getFormulaID() {
        return formulaID;
    }

    public void setFormulaID(Integer formulaID) {
        this.formulaID = formulaID;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}

