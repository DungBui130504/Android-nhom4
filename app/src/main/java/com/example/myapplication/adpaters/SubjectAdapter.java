package com.example.myapplication.adpaters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Subject.SubjectObject;

import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter {
    Activity context;
    int ID;
    ArrayList<SubjectObject> myList;

    public SubjectAdapter(Activity context, ArrayList<SubjectObject> myList, int ID) {
        super(context, ID ,  myList);
        this.context = context;
        this.myList = myList;
        this.ID = ID;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tao de chua layout
        LayoutInflater myFlater = context.getLayoutInflater(); // chinh la activity duoc dung
        //Dat ID layout len de vua tao -> thanh View
        convertView = myFlater.inflate(ID, null);
        //Lay 1 phan tu trong mang
        SubjectObject sb = myList.get(position);
        //Khai bao, tham chieu ID va thong tin nhan vien len
        TextView subjectItem = convertView.findViewById(R.id.subjectItem);

        subjectItem.setText(sb.getSubjectName());

        return convertView;
    }
}
