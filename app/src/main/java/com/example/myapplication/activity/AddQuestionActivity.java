package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.models.QuestionAnswer.QuestionAnswerTable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddQuestionActivity extends AppCompatActivity {
    ImageButton backBtn;
    EditText questionTxt, answserTxt;
    ImageButton submit;

    Intent intent = getIntent();
    //        int userId = intent.getIntExtra("userId", -2);
//        int subjectId = intent.getIntExtra("subjectId", -1);
    int userId = 1;
    int subjectId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backBtn = findViewById(R.id.backBtn);
        questionTxt = findViewById(R.id.questionTxt);
        answserTxt = findViewById(R.id.answserTxt);
        submit = findViewById(R.id.submit);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(AddQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String questionDate = sdf.format(calendar.getTime());
                    String answerDate = "";

                    if (questionTxt.getText().toString().isEmpty()) {
                        Toast.makeText(AddQuestionActivity.this, "Bạn phải nhập nội dung câu hỏi!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!answserTxt.getText().toString().isEmpty()) {
                        answerDate = questionDate;
                    }

                    // Tạo Intent để gửi dữ liệu trở lại
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("questionTxt", questionTxt.getText().toString());
                    resultIntent.putExtra("answserTxt", answserTxt.getText().toString());
                    resultIntent.putExtra("questionDate", questionDate);
                    resultIntent.putExtra("answerDate", answerDate);

                    setResult(RESULT_OK, resultIntent); // Đặt kết quả
                    finish();
                } catch (Exception e) {
                    Toast.makeText(AddQuestionActivity.this, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}