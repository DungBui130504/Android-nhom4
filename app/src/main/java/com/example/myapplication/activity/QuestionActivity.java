package com.example.myapplication.activity;

import android.annotation.SuppressLint;
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
import com.example.myapplication.adpaters.QuestionAdapter;
import com.example.myapplication.models.QuestionAnswer.QuestionAnswerObject;
import com.example.myapplication.models.QuestionAnswer.QuestionAnswerTable;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    ImageButton questionBack, addBtn;
    ListView questionList;
    ArrayList<QuestionAnswerObject> questions;
    QuestionAdapter questionAdapter;
    QuestionAnswerTable questionAnswerTable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -2);

        questionBack = findViewById(R.id.questionBack);
        addBtn = findViewById(R.id.addBtn);
        questionList = findViewById(R.id.questionList);
        questions = new ArrayList<>();

        questionAnswerTable = new QuestionAnswerTable(this);

        questions.addAll(questionAnswerTable.getQuestionAnswersOfUserID(1));

        questionAdapter = new QuestionAdapter(QuestionActivity.this, questions, R.layout.question_item);


        questionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, "Không trở về được!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(QuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(QuestionActivity.this, AddQuestionActivity.class);
                    startActivity(i);
                }
                catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}