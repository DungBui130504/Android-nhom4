package com.example.myapplication.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "hashPassword TEXT NOT NULL, " +
                "gmail TEXT NOT NULL, " +
                "phoneNumber TEXT);");

        db.execSQL("CREATE TABLE Contact (" +
                "email TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "type boolean NOT NULL);");

        db.execSQL("CREATE TABLE Subject (" +
                "subjectID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL);");

        db.execSQL("CREATE TABLE Formula (" +
                "formulaID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "formula TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "formulaCreateDate TEXT NOT NULL, " +
                "formulaUpdateDate TEXT, " +
                "subjectID INTEGER NOT NULL, " +
                "userID INTEGER NOT NULL, " +
                "FOREIGN KEY (subjectID) REFERENCES Subject(subjectID), " +
                "FOREIGN KEY (userID) REFERENCES User(userID));");

        db.execSQL("CREATE TABLE QuestionAnswer (" +
                "questionID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT NOT NULL, " +
                "answer TEXT, " +
                "answerUpdateDate TEXT, " +
                "answerCreateDate TEXT, " +
                "questionCreateDate TEXT NOT NULL, " +
                "questionUpdateDate TEXT, " +
                "subjectID INTEGER NOT NULL, " +
                "answerStatus TEXT CHECK (answerStatus IN ('answered', 'not answered')) NOT NULL, " +
                "userID INTEGER NOT NULL, " +
                "FOREIGN KEY (subjectID) REFERENCES Subject(subjectID), " +
                "FOREIGN KEY (userID) REFERENCES User(userID));");

        db.execSQL("CREATE TABLE Notification (" +
                "notificationCreateDate TEXT PRIMARY KEY, " +
                "notificationDateTime TEXT NOT NULL, " +
                "description TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Contact");
        db.execSQL("DROP TABLE IF EXISTS Subject");
        db.execSQL("DROP TABLE IF EXISTS Formula");
        db.execSQL("DROP TABLE IF EXISTS QuestionAnswer");
        db.execSQL("DROP TABLE IF EXISTS Notification");
        onCreate(db);
    }
}