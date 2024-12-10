package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.adpaters.SubjectAdapter;
import com.example.myapplication.models.Subject.SubjectObject;
import com.example.myapplication.models.Subject.SubjectTable;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {
    ImageButton subjectBack;
    ListView subjectList;
    SubjectAdapter subjectAdapter;
    ArrayList<SubjectObject> subjects;
    SubjectTable subjectTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subject);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        subjectBack = findViewById(R.id.subjectBack);
        subjectList = findViewById(R.id.subjectList);


        subjects = new ArrayList<>();
        subjects.add(new SubjectObject(1, "Tiếng anh", 1));
        subjects.add(new SubjectObject(2, "Toán", 1));
        subjects.add(new SubjectObject(3, "Lịch sử", 1));
        subjects.add(new SubjectObject(4, "Ngữ văn", 1));

        subjectAdapter = new SubjectAdapter(SubjectActivity.this, subjects, R.layout.subject_item);
        subjectList.setAdapter(subjectAdapter);

        subjectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    Intent i = new Intent(SubjectActivity.this, DashBoardActivity.class);
//                    startActivity(i);
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(SubjectActivity.this, "Không thể chuyển hướng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}