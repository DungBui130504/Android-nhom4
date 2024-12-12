package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    Button addSubjectBtn;
    TextView cancelBtn;
    ListView subjectList;
    LinearLayout addSubjectLayout, addSubjectBox;
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
        addSubjectBtn = findViewById(R.id.addSubjectBtn);
        addSubjectLayout = findViewById(R.id.addSubjectLayout);
        addSubjectBox = findViewById(R.id.addSubjectBox);
        cancelBtn = findViewById(R.id.cancelBtn);

        subjectTable = new SubjectTable(this);

        Log.d("Subjects: ", subjectTable.getSubjectsOfUserID(1).toString());

        subjects = new ArrayList<>();
        subjects.add(new SubjectObject(1, "Tiếng anh", 1));
        subjects.add(new SubjectObject(2, "Toán", 1));
        subjects.add(new SubjectObject(3, "Lịch sử", 1));
        subjects.add(new SubjectObject(4, "Ngữ văn", 1));
        subjects.add(new SubjectObject(4, "Ngữ văn", 1));
        subjects.add(new SubjectObject(4, "Ngữ văn", 1));
        subjects.add(new SubjectObject(4, "Ngữ văn", 1));
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

        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addSubjectLayout.setVisibility(View.VISIBLE);
                    addSubjectBox.setVisibility(View.VISIBLE);
                }
                catch (Exception e) {
                    Toast.makeText(SubjectActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addSubjectLayout.setVisibility(View.GONE);
                    addSubjectLayout.setClickable(false);
                    addSubjectBox.setVisibility(View.GONE);
                }
                catch (Exception e) {
                    Toast.makeText(SubjectActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Toast.makeText(SubjectActivity.this, "Subject selected", Toast.LENGTH_SHORT).show();
                    Intent iQuestion = new Intent(SubjectActivity.this, QuestionActivity.class);
                    startActivity(iQuestion);
                }
                catch (Exception e) {
                    Toast.makeText(SubjectActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}