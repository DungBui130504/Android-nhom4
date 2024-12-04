package com.example.myapplication.models.Formula;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.myapplication.Constants.FormulaConstants;
import com.example.myapplication.Constants.SubjectConstants;
import com.example.myapplication.Constants.UserConstants;
import com.example.myapplication.database.Database;

import java.time.LocalDate;

public class FormulaTable {



    private SQLiteDatabase db;
    Context context;

    public FormulaTable (Context context){
        this.context = context;
        try {
            this.db = new Database(context).open();
        } catch (Exception e) {
            Toast.makeText(context,"Có lỗi khi kết nốt db tại model formula : "+ e , Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addNewFormula(Integer formulaID, String formulaName, String formula, int userID, Long subjectID, String updatedDate) {
        if(isFormulaExistedByName(formulaName)){
            Toast.makeText(this.context,"Tên công thức đã tồn tại! " , Toast.LENGTH_SHORT).show();
            return false ;
        }
        String addFormulaStatement = "insert into Formula (formulaID, formulaName, formula, userID ,subjectID, updatedDate) values (?,?,?,?,?)";
        try{
            Cursor add = this.db.rawQuery(addFormulaStatement ,new String[]{formulaID.toString() ,formulaName, formula, Integer.toString(userID), subjectID.toString(), LocalDate.now().toString()});
            add.close();
            return true;
        } catch (Exception e) {
            Toast.makeText(this.context,"Có lỗi khi thêm mới công thức : "+ e , Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean deleteFormulaByID(int formulaID){
        try {
            String queryFormula = String.format("DELETE FROM Formula WHERE %s = %s",FormulaConstants.FORMULA_COLUMN_ID,formulaID);
            Cursor cur = this.db.rawQuery(queryFormula , null);
            cur.close();
            return true;
        } catch (Exception e) {
            Toast.makeText(this.context,"Có lỗi khi xóa formula : "+ e , Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isFormulaExistedByName(String formulaName){
        String queryFormula = String.format("select * from Formula where %s = ?", FormulaConstants.FORMULA_COLUMN_NAME);
        Cursor formulaFound = this.db.rawQuery(queryFormula , new String[]{formulaName});
        boolean exists = formulaFound.moveToFirst();
        formulaFound.close();
        return exists;
    }
    public FormulaObject getFormulaById(int formulaID) {
        FormulaObject formula = null;

        String queryFormula = String.format("SELECT * FROM Formula WHERE %s = ?", FormulaConstants.FORMULA_COLUMN_ID);
        Cursor cursor = null;

        try {
            cursor = this.db.rawQuery(queryFormula, new String[]{String.valueOf(formulaID)});

            if (cursor != null && cursor.moveToFirst()) {
                int formulaIdIndex = cursor.getColumnIndex(FormulaConstants.FORMULA_COLUMN_ID);
                int formulaNameIndex = cursor.getColumnIndex(FormulaConstants.FORMULA_COLUMN_NAME);
                int formulaIndex = cursor.getColumnIndex(FormulaConstants.FORMULA_COLUMN_FORMULA);
                int userIdIndex = cursor.getColumnIndex(UserConstants.USER_COLUMN_ID);
                int subjectIdIndex = cursor.getColumnIndex(SubjectConstants.SUBJECT_COLUMN_ID);
                int createdDateIndex = cursor.getColumnIndex(FormulaConstants.FORMULA_COLUMN_UPDATED_DATE);

                if (formulaIdIndex >= 0 && formulaNameIndex >= 0 && formulaIndex >= 0 && createdDateIndex >= 0) {
                    Integer id = cursor.getInt(formulaIdIndex);
                    String name = cursor.getString(formulaNameIndex);
                    String formulaContent = cursor.getString(formulaIndex);
                    String createdDate = cursor.getString(createdDateIndex);
                    Integer subjectID = cursor.getInt(subjectIdIndex);
                    Integer userID = cursor.getInt(userIdIndex);

                    formula = new FormulaObject(id, name, formulaContent, userID, subjectID, createdDate);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi lấy thông tin công thức: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return formula;
    }
}
