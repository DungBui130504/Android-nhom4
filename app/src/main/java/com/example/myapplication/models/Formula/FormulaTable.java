package com.example.myapplication.models.Formula;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.myapplication.database.Database;
import com.example.myapplication.models.Contact.ContactObject;

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
    public boolean addNewFormula(Long formulaID, String formulaName, String formula, int userID, Long subjectID) {
        if(isFormulaExistedByName(formulaName)){
            Toast.makeText(this.context,"Username đã tồn tại! " , Toast.LENGTH_SHORT).show();
            return false ;
        }
        String addFormulaStatement = "insert into Formula (formulaID, formulaName, formula, userID ,subjectID, ) values (?,?,?,?,?)";
        try{
            Cursor add = this.db.rawQuery(addFormulaStatement ,new String[]{formulaID.toString() ,formulaName, formula, Integer.toString(userID), subjectID.toString(), LocalDate.now().toString()});
            add.close();
            return true;
        } catch (Exception e) {
            Toast.makeText(this.context,"Có lỗi khi thêm mới user : "+ e , Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean deleteFormulaByID(int formulaID){
        try {
            String queryFormula = "DELETE FROM User WHERE userID=" + formulaID;
            Cursor cur = this.db.rawQuery(queryFormula , null);
            cur.close();
            return true;
        } catch (Exception e) {
            Toast.makeText(this.context,"Có lỗi khi xóa formula : "+ e , Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isFormulaExistedByName(String formulaName){
        String queryFormula = "select * from Formula where formulaName = ?";
        Cursor formulaFound = this.db.rawQuery(queryFormula , new String[]{formulaName});
        boolean exists = formulaFound.moveToFirst();
        formulaFound.close();
        return exists;
    }
    public FormulaObject getFormulaById(int formulaID) {
        FormulaObject formula = null;

        String queryFormula = "SELECT * FROM Formula WHERE FormulaID = ?";
        Cursor cursor = null;

        try {
            cursor = this.db.rawQuery(queryFormula, new String[]{String.valueOf(formulaID)});

            if (cursor != null && cursor.moveToFirst()) {
                int formulaIdIndex = cursor.getColumnIndex("formulaID");
                int formulaNameIndex = cursor.getColumnIndex("formulaName");
                int formulaIndex = cursor.getColumnIndex("formula");
                int userIdIndex = cursor.getColumnIndex("userID");
                int subjectIdIndex = cursor.getColumnIndex("subjectID");
                int createdDateIndex = cursor.getColumnIndex("formulaCreatedDate");

                if (formulaIdIndex >= 0 && formulaNameIndex >= 0 && formulaIndex >= 0 && createdDateIndex >= 0) {
                    Long id = cursor.getLong(formulaIdIndex);
                    String name = cursor.getString(formulaNameIndex);
                    String formulaContent = cursor.getString(formulaIndex);
                    String createdDate = cursor.getString(createdDateIndex);
                    Long subjectID = cursor.getLong(subjectIdIndex);
                    int userID = cursor.getInt(userIdIndex);

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
