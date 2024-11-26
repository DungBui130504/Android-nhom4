package com.example.myapplication.models.User;

public class UserObject {
    public String userName;
    public String passWord;
    public String gmail;
    public String fullName;
    public String phoneNumber;
    public UserObject(String userName, String password,String gmail ,String fullName, String phoneNumber){
        this.userName = userName;
        this.passWord = password;
        this.gmail = gmail;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
    public UserObject(){
        this.userName = "none";
        this.passWord = "none";
        this.gmail = "none";
        this.fullName = "none";
        this.phoneNumber = "none";
    }
}
