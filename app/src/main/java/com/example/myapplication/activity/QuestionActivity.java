package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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
    TextView numOfQuestion;
    ArrayList<QuestionAnswerObject> questions;
    QuestionAdapter questionAdapter;
    QuestionAnswerTable questionAnswerTable;

    Intent intent = getIntent();
    //        int userId = intent.getIntExtra("userId", -2);
//        int subjectId = intent.getIntExtra("subjectId", -2);
    int userId = 1;
    int subjectId = 1;

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

        questionBack = findViewById(R.id.questionBack);
        addBtn = findViewById(R.id.addBtn);
        questionList = findViewById(R.id.questionList);
        numOfQuestion = findViewById(R.id.numOfQuestion);

        questions = new ArrayList<>();

        questionAnswerTable = new QuestionAnswerTable(this);

        questions.addAll(questionAnswerTable.getQuestionAnswersOfUserID(1, 1));

        questionAdapter = new QuestionAdapter(QuestionActivity.this, questions, R.layout.question_item);

        questionList.setAdapter(questionAdapter);

        questionAdapter.notifyDataSetChanged();

        numOfQuestion.setText(questionAnswerTable.getCountOfQuestions(subjectId, userId));

        questionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, "Không trở về được!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(QuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionActivity.this, AddQuestionActivity.class);
                i.putExtra("userId", userId);
                i.putExtra("subjectId", subjectId);
                startActivityForResult(i, 100); // Sử dụng requestCode 100
            }
        });

    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            try {
                // Lấy dữ liệu từ Intent trả về
                String questionTxt = data.getStringExtra("questionTxt");
                String answserTxt = data.getStringExtra("answserTxt");
                String answerDate = data.getStringExtra("answerDate");
                String questionDate = data.getStringExtra("questionDate");

                Log.d("questionTxt:", questionTxt);

                // Thêm câu hỏi mới vào database
                questionAnswerTable.addNewQuestionAnswer(
                        questionTxt, answserTxt, answerDate, questionDate, userId, subjectId
                );

                // Cập nhật danh sách câu hỏi
                questions.clear();
                questions.addAll(questionAnswerTable.getQuestionAnswersOfUserID(userId, subjectId));
                questionAdapter.notifyDataSetChanged();

                Toast.makeText(QuestionActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(QuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}