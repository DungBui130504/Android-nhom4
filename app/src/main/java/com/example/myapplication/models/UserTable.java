package com.example.myapplication.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.myapplication.database.Database;

public class UserTable {
    public SQLiteDatabase db;
    Context context;
    public UserTable (Context context){
        this.context = context;
        try {
            this.db = new Database(context).open();
        } catch (Exception e) {
            Toast.makeText(context,"Có lỗi khi kết nốt db tại model User : "+ e , Toast.LENGTH_SHORT).show();
        }
    }

    public void addNewUser(String userName, String password,String gmail , String phoneNumber){
        if(checkUserExisted(userName)){
            Toast.makeText(this.context,"Username đã tồn tại! " , Toast.LENGTH_SHORT).show();
            return;
        }
        String addUserStatement = "insert into User (username, password,gmail,phoneNumber) values (?,?,?,?) ";
        try{
            Cursor add = this.db.rawQuery(addUserStatement ,new String[]{userName,password,gmail,phoneNumber});
            add.close();
        } catch (Exception e) {
            Toast.makeText(this.context,"Có lỗi khi thêm mới user : "+ e , Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUserExisted(String userName){
        String queryUser = "select * from User where userName = ?";
        Cursor userFound = this.db.rawQuery(queryUser , new String[]{userName});
        boolean exists = userFound.moveToFirst();
        userFound.close();
        return exists;
    }

}
