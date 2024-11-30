package com.example.myapplication.adpaters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class SubjectAdapter extends ArrayAdapter {
    public SubjectAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SubjectAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
